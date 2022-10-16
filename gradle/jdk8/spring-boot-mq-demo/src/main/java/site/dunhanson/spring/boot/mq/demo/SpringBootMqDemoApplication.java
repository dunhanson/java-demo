package site.dunhanson.spring.boot.mq.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class SpringBootMqDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMqDemoApplication.class, args);
    }

}
