package site.dunhanson.spring.boot.mq.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    @KafkaListener(topics = "test")
    public void consumer(String message) throws InterruptedException {
        log.info("consumer start...");
        log.info("message:{}", message);
        // 睡眠5分钟多1秒，看超时重复消费问题
        Thread.sleep(1000 * 60 * 5 + 1000);
        log.info("consumer end.");
    }
}
