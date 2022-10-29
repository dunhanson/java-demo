package site.dunhanson.jdk17.demo;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class DemoTest {
    public static void main(String[] args) {
        URI uri = URI.create("https://www.baidu.com");
        uri = UriBuilder.fromUri(uri)
                .queryParam("a", "aaa")
                .queryParam("b", "bbb").build();
        System.out.println(uri.toString());
    }
}
