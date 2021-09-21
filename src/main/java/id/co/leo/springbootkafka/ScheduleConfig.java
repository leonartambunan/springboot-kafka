package id.co.leo.springbootkafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;


@Configuration
@EnableScheduling
public class ScheduleConfig {

    final TopicProducerService topicProducerService;

    public ScheduleConfig(TopicProducerService topicProducerService) {
        this.topicProducerService = topicProducerService;
    }

    @Scheduled(fixedDelay = 10000) //Every 10 seconds
    public void publishMessage() {
        logger.info("publishMessage");
        topicProducerService.sendMessage(new Date().toString());
    }

    private static  final Logger logger = LoggerFactory.getLogger(ScheduleConfig.class);
}

