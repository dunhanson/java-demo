package site.dunhanson.concurrency.demo.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个有固定数量的线程池
 */
public class FixedThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);
    }
}
