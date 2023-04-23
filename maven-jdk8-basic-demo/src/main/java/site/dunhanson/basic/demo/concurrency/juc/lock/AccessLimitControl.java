package site.dunhanson.basic.demo.concurrency.juc.lock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 最多允许3个线程同时访问
 * <p>Semaphore本质上就是一个信号计数器，用于限制同一时间的最大访问数量。</p>
 * @author dunhanson
 * @since 2022-10-31
 */
public class AccessLimitControl {
    final Semaphore semaphore = new Semaphore(3);

    public String access() throws InterruptedException {
        semaphore.acquire();
        try {
            Thread.sleep(5000);
            return Thread.currentThread().getName() + "-"
                    + UUID.randomUUID() + "-"
                    + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        AccessLimitControl control = new AccessLimitControl();
        ExecutorService service = Executors.newFixedThreadPool(4);
        for(int i = 1; i <= 4; i++) {
            service.submit(()->{
                try {
                    System.out.println(control.access());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
