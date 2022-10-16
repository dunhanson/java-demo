package site.dunhanson.spring.boot.mq.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @KafkaListener(topics = "test")
    public void test(String message) {
        System.out.println(message);
    }
}
