package site.dunhanson.concurrency.demo.completable;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture例子-异步执行任务
 * @author dunhanson
 * @since 2023-06-18
 * @version 1.0.0
 */
@Slf4j
public class CompletableFutureExample {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            String str = "hello,world";
            log.info("str:{}", str);
            return str;
        });
        log.info("say:{}", completableFuture.join());
        System.out.println("finish.");
    }
}
