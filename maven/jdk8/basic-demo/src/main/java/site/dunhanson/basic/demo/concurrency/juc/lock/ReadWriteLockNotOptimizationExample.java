package site.dunhanson.basic.demo.concurrency.juc.lock;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入读写锁-未优化代码
 * <p>问题：get()方法并不会对数据产生影响，实际不需要通过加锁来保护的</p>
 * @author dunhanson
 * @since 2022-10-29
 */
public class ReadWriteLockNotOptimizationExample {
    private final Lock lock = new ReentrantLock();
    private List<String> dataList = new ArrayList<>();

    public ReadWriteLockNotOptimizationExample() {
        for(int i = 0; i < 1000; i++) {
            dataList.add(String.valueOf(i));
        }
    }

    public void add(String data) {
        lock.lock();
        try {
            dataList.add(data);
        } finally {
            lock.unlock();
        }
    }

    public String get(int idx) {
        lock.lock();
        try {
            Thread.sleep(10);
            return dataList.get(idx);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockNotOptimizationExample example = new ReadWriteLockNotOptimizationExample();
        ExecutorService service = Executors.newFixedThreadPool(6);
        LocalDateTime start = LocalDateTime.now();
        for(int i = 0; i < 1000; i++) {
            int finalI = i;
            service.submit(()-> System.out.println(example.get(finalI)));
        }
        service.shutdown();
        while (!service.isTerminated()) {
            System.out.println("线程池关闭中...");
        }
        // 耗时:15s
        System.out.println("耗时:" + Duration.between(start, LocalDateTime.now()).getSeconds() + "s");
    }
}
