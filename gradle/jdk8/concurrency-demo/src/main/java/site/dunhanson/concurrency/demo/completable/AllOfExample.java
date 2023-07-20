package site.dunhanson.concurrency.demo.completable;

import lombok.extern.slf4j.Slf4j;
import site.dunhanson.concurrency.demo.utils.CommonUtils;

import java.util.concurrent.CompletableFuture;
/**
 * CompletableFuture例子-allOf
 * <p>两个任务全部执行完成再执行其他任务</p>
 * <p>两个任务全部执行完成再执行其他任务</p>
 * @author dunhanson
 * @since 2023-06-18
 * @version 1.0.0
 */
@Slf4j
public class AllOfExample {
    public static void main(String[] args) {
        CompletableFuture<Void> c1 = CompletableFuture.runAsync(() -> log.info("no return value for c1"));
        CompletableFuture<Void> c2 = CompletableFuture.runAsync(() -> log.info("no return value for c2"));
        CompletableFuture.allOf(c1, c2).join();
        CommonUtils.finish();
    }
}
