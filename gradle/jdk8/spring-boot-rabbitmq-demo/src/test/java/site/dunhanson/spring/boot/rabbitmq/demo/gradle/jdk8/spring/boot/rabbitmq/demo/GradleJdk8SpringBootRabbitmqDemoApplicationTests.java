package site.dunhanson.spring.boot.rabbitmq.demo.gradle.jdk8.spring.boot.rabbitmq.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@SpringBootTest
class GradleJdk8SpringBootRabbitmqDemoApplicationTests {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 普通消息
     */
    @Test
    void test1() {
        Assertions.assertTrue(true);
        rabbitTemplate.send("amq.direct", "test", new Message("hello, rabbitmq".getBytes()));
    }

    /**
     * 延迟消息
     * <p>DLX和TTL来实现延迟队列</p>
     */
    @Test
    void test2() {
        Assertions.assertTrue(true);
        long delayInMilliseconds = 1000 * 60;
        Message rabbitMessage = MessageBuilder.withBody("hello, rabbitmq".getBytes())
                .build();
        rabbitTemplate.convertAndSend("original_exchange", "original_routing_key", rabbitMessage, (message -> {
            message.getMessageProperties().setExpiration(String.valueOf(delayInMilliseconds));
            return message;
        }));
    }

}
