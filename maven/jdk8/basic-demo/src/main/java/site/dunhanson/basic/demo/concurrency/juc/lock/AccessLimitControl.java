package site.dunhanson.basic.demo.concurrency.juc.lock;

import java.util.UUID;
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
            return UUID.randomUUID().toString();
        } finally {
            semaphore.release();
        }
    }
}
