package site.dunhanson.gradle.jdk8.spring.security.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 * @author dunhanson
 * @since 2023-04-24
 * @version 1.0.0
 */
@RestController
public class LoginController {
    @RequestMapping("/index")
    public String index() {
        return "login success";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello spring security";
    }
}
