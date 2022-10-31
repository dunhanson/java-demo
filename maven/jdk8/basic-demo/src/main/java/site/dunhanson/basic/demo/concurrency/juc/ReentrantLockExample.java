package site.dunhanson.basic.demo.concurrency.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock，重入锁，属于排它锁类型，功能和synchronized相似
 * <p>ReentrantLockExample内部有ReentrantLock、count属性</p>
 * <p>1、创建一个ReentrantLockExample对象</p>
 * <p>2、并且依次创建两个线程</p>
 * <p>3、线程内部执行ReentrantLockExample对象的incr()方法，对count参数进行自增（循环一万次）</p>
 * @author dunhanson
 * @since 2022-10-29
 */
public class ReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;
    public void incr() {

        lock.lock();
        count++;
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockExample example = new ReentrantLockExample();
        Thread[] threads = new Thread[2];
        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for(int j = 0; j < 10000; j++) {
                    example.incr();
                }
            });
            threads[i].start();
        }
        threads[0].join();
        threads[1].join();
        System.out.println(example.count);
    }
}
