package site.dunhanson.concurrency.demo.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private List<String> dataList = new ArrayList<>();

    public void add(String data) {
        System.out.println(Thread.currentThread().getId() + "-add方法, try get lock");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "-add方法, lock");
            dataList.add(data);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread().getId() + "-add方法, unlock");
            writeLock.unlock();
        }
    }

    public String get(int idx) {
        System.out.println(Thread.currentThread().getId() + "-get方法, try get lock");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "-get方法, lock");
            return dataList.get(idx);
        } finally {
            System.out.println(Thread.currentThread().getId() + "-get方法, unlock");
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockExample example = new ReadWriteLockExample();
        Thread aaa = new Thread(() -> {
            example.add("aaa");
        });
        aaa.start();
        Thread.sleep(100);
        Thread bbb = new Thread(() -> {
            example.get(0);
        });
        bbb.start();
        aaa.join();
        bbb.join();
        System.out.println(example.dataList);
    }


}
