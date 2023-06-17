package site.dunhanson.spring.boot.mongo.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping
    public String hello() {
        return "hello, spring-boot-mongo-demo";
    }
}