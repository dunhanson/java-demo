package site.dunhanson.basic.demo.concurrency.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子操作
 * <p>Atomic类是通过无锁（lock-free）的方式实现的线程安全（thread-safe）访问。它的主要原理是利用了CAS：Compare and Set。
 * @author dunhanson
 * @since 2022-10-31
 */
public class AtomicExample {
    // 1935
    //Integer count = 0;
    AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        AtomicExample example = new AtomicExample();
        ExecutorService service = Executors.newFixedThreadPool(4);
        for(int i = 1; i <= 4; i++) {
            service.submit(()->{
                for(int j = 0; j < 1000; j++) {
                    //example.count++;
                    example.count.addAndGet(1);
                }
            });
        }
        service.shutdown();
        Thread.sleep(5 * 1000);
        while (!service.isTerminated()) {

        }
        System.out.println("count:" + example.count);
    }
}
