package site.dunhanson.concurrency.demo.basic;

/**
 * 线程死锁例子
 * @author dunhanson
 * @since 2023-06-19
 * @version 1.0.0
 */
public class ThreadDeadBlockExample extends Thread {
    private final Object lock1;
    private final Object lock2;

    public ThreadDeadBlockExample(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            try {
                System.out.println("Thread " + this.getName() + " is holding lock1.");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread " + this.getName() + " is interrupted.");
            }
            synchronized (lock2) {
                System.out.println("Thread " + this.getName() + " is holding lock1 and lock2.");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object lock1 = new Object();
        Object lock2 = new Object();

        ThreadDeadBlockExample t1 = new ThreadDeadBlockExample(lock1, lock2);
        ThreadDeadBlockExample t2 = new ThreadDeadBlockExample(lock2, lock1);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("All threads are finished.");
    }
}