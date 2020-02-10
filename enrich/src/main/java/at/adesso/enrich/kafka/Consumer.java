package at.adesso.enrich.kafka;

import at.adesso.enrich.model.TurbineData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final Producer producer;

    @Autowired
    Consumer(Producer producer) {
        this.producer = producer;
    }

    @KafkaListener(topics = "turbine-raw", groupId = "consumer-0")
    public void consume(String message) throws IOException {
        String[] values = message.split(";");

        if (values.length == 7) {
            TurbineData temp = new TurbineData(values[0], values[1], values[2],
                    Float.parseFloat(values[3].replace(",", ".")),
                    Float.parseFloat(values[4].replace(",", ".")),
                    Boolean.parseBoolean(values[5]), Float.parseFloat(values[6].replace(",", ".")));

            evaluateTurbine(temp);

            this.producer.sendMessage(temp);

            logger.info(String.format("#### -> Consumed message -> %s", message));
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
}