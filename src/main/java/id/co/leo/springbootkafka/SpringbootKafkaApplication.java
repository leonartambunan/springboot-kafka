package id.co.leo.springbootkafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;


@SpringBootApplication
public class SpringbootKafkaApplication {
	private static final Logger logger = LoggerFactory.getLogger(SpringbootKafkaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootKafkaApplication.class, args);
	}

	@KafkaListener(topics = "${kafka.topic_name}", groupId = "${kafka.group_id}", id = "horas")
	public void listener(String message) {
		logger.info("recv timestamp: {} ",System.currentTimeMillis());
		logger.info("Incoming message : {}", message);
	}
}
