package site.dunhanson.jdk17.demo;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.security.URIParameter;

public class HttpClientExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create("http://apis.juhe.cn/ip/ipNewV3?");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        String result = client.send(request, HttpResponse.BodyHandlers.ofString()).body().toString();
        System.out.println(result);
    }
}
