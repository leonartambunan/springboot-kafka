package id.co.leo.springbootkafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TopicProducerService {
    private static final Logger logger = LoggerFactory.getLogger(TopicProducerService.class);

    @Value(value = "${kafka.topic_name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        logger.info("Outgoing message : {}",message);
        logger.info("sent timestamp: {} ",System.currentTimeMillis());
        this.kafkaTemplate.send(topicName, message);

    }
}
