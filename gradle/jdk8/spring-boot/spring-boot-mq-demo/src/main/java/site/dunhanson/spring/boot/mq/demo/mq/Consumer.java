package site.dunhanson.spring.boot.mq.demo.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    @KafkaListener(topics = "test")
    public void test(String message) {
        log.info("message:{}", message);
    }
}
