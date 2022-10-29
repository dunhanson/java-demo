package site.dunhanson.basic.demo.concurrency.juc;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 可重入读写锁-未优化代码
 * <p>ReentrantReadWriteLock
 * 在读多写少的场景中，这种方式能够极大地提升程序地性能</p>
 * @author dunhanson
 * @since 2022-10-29
 */
public class ReadWriteLockOptimizedExample {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private List<String> dataList = new ArrayList<>();

    public ReadWriteLockOptimizedExample() {
        for(int i = 0; i < 1000; i++) {
            dataList.add(String.valueOf(i));
        }
    }

    public void add(String data) {
        writeLock.lock();
        try {
            dataList.add(data);
        } finally {
            writeLock.unlock();
        }
    }

    public String get(int idx) {
        readLock.lock();
        try {
            Thread.sleep(10);
            return dataList.get(idx);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockOptimizedExample example = new ReadWriteLockOptimizedExample();
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
        // 耗时:3s
        System.out.println("耗时:" + Duration.between(start, LocalDateTime.now()).getSeconds() + "s");
    }
}
