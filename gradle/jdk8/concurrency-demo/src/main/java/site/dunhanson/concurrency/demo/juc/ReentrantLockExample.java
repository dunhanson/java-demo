package site.dunhanson.concurrency.demo.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建两个线程，每个线程循环10000次，进行同一个对象中的count变量，进行自增操作。
 * 自增操作方法中使用ReentrantLock
 */
public class ReentrantLockExample {
    static Lock lock = new ReentrantLock();
    private int count = 0;

    public void incr() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockExample example = new ReentrantLockExample();
        Thread[] threads = new Thread[2];
        for (int j = 0; j < 2; j++) {
            threads[j] = new Thread(()->{
                for(int k = 0; k < 10000; k++) {
                    example.incr();
                }
            });
            threads[j].start();
        }
        threads[0].join();
        threads[1].join();
        System.out.println(example.count);
    }
}
