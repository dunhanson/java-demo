package site.dunhanson.spring.boot.rabbitmq.demo.gradle.jdk8.spring.boot.rabbitmq.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class Consumer {
    @RabbitListener(queues = "test")
    public void message(String message) {
        log.info("test received message: {}", message);
    }

    //@RabbitListener(queues = "dlx_queue")
    public void dlxQueueMessage(String message) {
        log.info("dlx_queue received message:{}", message);
    }
}
