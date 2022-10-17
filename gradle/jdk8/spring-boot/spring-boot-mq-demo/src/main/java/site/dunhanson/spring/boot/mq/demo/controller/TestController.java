package site.dunhanson.spring.boot.mq.demo.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/test")
    public String test() {
        kafkaTemplate.send("test", "hello");
        return "hello,word";
    }
}
