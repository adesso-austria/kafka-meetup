package at.adesso.api.kafka;

import at.adesso.api.model.Turbine;
import at.adesso.api.repository.TurbineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private TurbineRepository turbineRepository;

    @KafkaListener(topics = "turbine-agg", groupId = "consumer-1")
    public void consume(String message) throws IOException {
        String[] values = message.split(";");

        if (values.length == 8) {
            Turbine temp = new Turbine(values[0], values[1], values[2],
                    Float.parseFloat(values[3].replace(",", ".")),
                    Float.parseFloat(values[4].replace(",", ".")),
                    Boolean.parseBoolean(values[5]), Float.parseFloat(values[6].replace(",", ".")),
                    Turbine.TurbineStatus.valueOf(values[7]));
            turbineRepository.save(temp);
            logger.info(String.format("#### -> Consumed message -> %s", message));
        } else {
            logger.error(String.format("Message %s has wrong format", message));
        }
    }

}