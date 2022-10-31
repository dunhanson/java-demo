package site.dunhanson.basic.demo.concurrency.juc.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * StampedLock
 * <p>进一步提升并发执行效率的ReadWriteLock<br>
 * StampedLock把读锁细分为乐观读和悲观读，能进一步提升并发效率<br>
 * 乐观锁的意思就是乐观地估计读的过程中大概率不会有写入，因此被称为乐观锁。<br>
 * 反过来，悲观锁则是读的过程中拒绝有写入，也就是写入必须等待。<br>
 * 显然乐观锁的并发效率更高，但一旦有小概率的写入导致读取的数据不一致，需要能检测出来，再读一遍就行。
 * @author dunhanson
 * @since 2022-10-30
 */
public class StampedLock1Example {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private double x;
    private double y;

    public StampedLock1Example() {
        this.x = 10;
        this.y = 10;
    }

    public void move(double deltaX, double deltaY) {
        writeLock.lock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            writeLock.unlock();
        }
    }

    public double distanceFromOrigin() {
        double currentX = x;
        double currentY = y;
        readLock.lock();
        try {
            Thread.sleep(1000);
            currentX = x;
            currentY = y;
            return Math.sqrt(currentX * currentX + currentY * currentY);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> ts = new ArrayList<>();
        StampedLock1Example example = new StampedLock1Example();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(()->{
                while (true) {
                    System.out.println(Thread.currentThread().getName() + ": " + example.distanceFromOrigin());
                }
            });
            ts.add(thread);
            thread.start();
        }
        Thread thread = new Thread(() -> {
            System.out.println("move before.");
            example.move(20, 20);
            System.out.println("move after.");
        });
        thread.start();
        thread.join();
        Thread.sleep(60 * 1000 * 60);
        ts.forEach(Thread::interrupt);
        System.out.println("finish.");
    }
}
