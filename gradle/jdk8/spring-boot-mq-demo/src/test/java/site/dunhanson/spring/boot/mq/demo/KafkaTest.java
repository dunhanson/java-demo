package site.dunhanson.spring.boot.mq.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;

@SpringBootTest
class KafkaTest {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void test() {
        kafkaTemplate.send("test", "hello test");
    }

}
