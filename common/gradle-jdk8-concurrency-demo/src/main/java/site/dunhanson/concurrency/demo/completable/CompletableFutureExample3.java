package site.dunhanson.concurrency.demo.completable;

import lombok.extern.slf4j.Slf4j;
import site.dunhanson.concurrency.demo.common.CustomNameThreadFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static site.dunhanson.concurrency.demo.common.utils.ConcurrencyUtils.stopExecutorService;

/**
 * CompletableFuture例子-异组合多个CompletableFuture对象
 * @author dunhanson
 * @since 2023-06-18
 * @version 1.0.0
 */
@Slf4j
public class CompletableFutureExample3 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(
                5,
                new CustomNameThreadFactory("CompletableFutureExample2")
        );
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "say hello", executorService);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "say bye", executorService);
        CompletableFuture<Void> future3 = future1.thenCombineAsync(future2, (str1, str2) -> {
            log.info("str1:{},", str1);
            log.info("str2:{}", str2);
            return str1  + " " + str2;
        }, executorService).thenAcceptAsync(log::info, executorService);
        future3.join();
        log.info("finish.");
        stopExecutorService(executorService);
    }
}
