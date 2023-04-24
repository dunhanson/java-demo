package site.dunhanson.gradle.jdk8.spring.security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import java.util.Collection;

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

    @GetMapping("/user")
    public void userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println("name = " + name);
        System.out.println("authorities = " + authorities);
    }

    @RequestMapping("/authentication")
    public void authentication(Authentication authentication) {
        System.out.println("authentication = " + authentication);
    }

    @RequestMapping("/principal")
    public void principal(Principal principal) {
        System.out.println("principal = " + principal);

    }
}
