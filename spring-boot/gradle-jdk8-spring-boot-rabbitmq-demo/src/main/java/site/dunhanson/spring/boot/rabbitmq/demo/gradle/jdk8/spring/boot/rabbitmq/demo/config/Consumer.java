package site.dunhanson.spring.boot.rabbitmq.demo.gradle.jdk8.spring.boot.rabbitmq.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {
    @RabbitListener(queues = "topic_test")
    public void message(String message) {
        log.info("received message:{}", message);
    }
}
