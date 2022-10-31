package site.dunhanson.basic.demo.concurrency.basic;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture
 * <p>针对Future做了改进，可以传入回调对象，当异步任务完成或者发生异常时，自动调用回调对象的回调方法。
 * @author dunhanson
 * @since 2022-10-31
 */
public class CompletableFutureExample {
    public static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {}
        double v = Math.random();
        if(v < 0.3) {
            throw new RuntimeException("fetch price failed");
        }
        return 5 + v * 20;
    }

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(CompletableFutureExample::fetchPrice);
        future.thenAccept(System.out::println).exceptionally(e->{
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }
}
