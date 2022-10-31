package site.dunhanson.basic.demo.concurrency.juc.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

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
public class StampedLock2Example {
    private final StampedLock stampedLock = new StampedLock();
    private double x;
    private double y;

    public StampedLock2Example() {
        this.x = 10;
        this.y = 10;
    }

    public void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public double distanceFromOrigin() {
        long stamp = stampedLock.tryOptimisticRead();
        double currentX = x;
        double currentY = y;
        if(!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> ts = new ArrayList<>();
        StampedLock2Example example = new StampedLock2Example();
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
