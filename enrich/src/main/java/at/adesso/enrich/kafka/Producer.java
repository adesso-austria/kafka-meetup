package at.adesso.enrich.kafka;

import at.adesso.enrich.model.TurbineData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "turbine-agg";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(TurbineData message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.executeInTransaction(kt -> kt.send(TOPIC, message.toString()));
    }
}