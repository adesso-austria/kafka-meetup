package at.adesso.enrich.kafka;

import at.adesso.enrich.model.TurbineData;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final Producer producer;
    BloomFilter<String> filter;

    @Autowired
    Consumer(Producer producer) {
        this.filter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 500);
        this.producer = producer;
    }

    @KafkaListener(topics = "turbine-raw", groupId = "consumer-0")
    public void consume(String message) throws IOException {
        String[] values = message.split(";");

        if (values.length == 7) {
            // hash the message for bloom-filter comparison
            String messageHash = encryptThisString(message);

            if (!filter.mightContain(messageHash)) {
                TurbineData temp = new TurbineData(values[0], values[1], values[2],
                        Float.parseFloat(values[3].replace(",", ".")),
                        Float.parseFloat(values[4].replace(",", ".")),
                        Boolean.parseBoolean(values[5]), Float.parseFloat(values[6].replace(",", ".")));

                evaluateTurbine(temp);
                logger.info(String.format("#### -> Consumed message -> %s", message));
                filter.put(messageHash);
                this.producer.sendMessage(temp);
            } else {
                logger.info(String.format("Message %s was probably a duplicate", message));
            }

        } else {
            logger.error(String.format("Message %s has wrong format", message));
        }
    }

    private void evaluateTurbine(TurbineData turbine) {
        if ( turbine != null ) {
            // critical
            if ( turbine.getTempInside() <= 20.00 || turbine.getTempInside() > 80.00 ) {
                turbine.setStatus(TurbineData.TurbineStatus.CRITICAL);
            } else if ( turbine.getTempOutside() <= -40.00 || turbine.getTempOutside() >= 40.00 ) {
                turbine.setStatus(TurbineData.TurbineStatus.CRITICAL);
            } else if ( turbine.getWindSpeed() > 10.00 || turbine.isBats() ) {
                turbine.setStatus(TurbineData.TurbineStatus.DANGEROUS);
            } else {
                turbine.setStatus(TurbineData.TurbineStatus.OPERATIONAL);
            }
        }
    }

    public static String encryptThisString(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}