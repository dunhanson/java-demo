package site.dunhanson.concurrency.demo.completable;

import lombok.extern.slf4j.Slf4j;
import site.dunhanson.concurrency.demo.common.CustomNameThreadFactory;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static site.dunhanson.concurrency.demo.common.utils.ConcurrencyUtils.stopExecutorService;

/**
 * CompletableFuture例子-异步执行任务，使用自定义线程池
 * @author dunhanson
 * @since 2023-06-18
 * @version 1.0.0
 */
@Slf4j
public class CompletableFutureExample2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(
                5,
                new CustomNameThreadFactory("CompletableFutureExample2")
        );
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> "hello,world", executorService)
                .thenApply(String::toUpperCase)
                .thenAcceptAsync(log::info);
        future.join();
        System.out.println("finish.");
        stopExecutorService(executorService);
    }
}
