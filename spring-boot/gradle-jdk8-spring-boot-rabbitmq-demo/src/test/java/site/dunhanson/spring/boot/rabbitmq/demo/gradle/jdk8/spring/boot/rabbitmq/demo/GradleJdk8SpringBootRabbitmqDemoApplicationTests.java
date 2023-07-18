package site.dunhanson.spring.boot.rabbitmq.demo.gradle.jdk8.spring.boot.rabbitmq.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@SpringBootTest
class GradleJdk8SpringBootRabbitmqDemoApplicationTests {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        Assertions.assertTrue(true);
        rabbitTemplate.send("amq.topic", "test", new Message("hello, rabbitmq".getBytes()));
    }

}
