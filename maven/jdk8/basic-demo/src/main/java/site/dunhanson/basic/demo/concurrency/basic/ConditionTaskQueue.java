package site.dunhanson.basic.demo.concurrency.basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition实现版本
 * @author dunhanson
 * @since 2022-10-30
 */
public class ConditionTaskQueue {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    Queue<String> queue = new LinkedList<>();

    public void addTask(String s) {
        lock.lock();
        try {
            this.queue.add(s);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String getTask() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                condition.await();
            }
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 1、创建五个t线程，线程内部while(true)循环：从队列当中获取数据，然后进行打印
     * 2、创建一个add线程，添加是个随机数到队列当中
     * @param args String[]
     */
    public static void main(String[] args) throws InterruptedException {
        // 1、创建五个t线程，线程内部while(true)循环：从队列当中获取数据，然后进行打印（其实就是模拟五个消费端线程）
        ConditionTaskQueue q = new ConditionTaskQueue();
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> {
               while (true) {
                   try {
                       String s = q.getTask();
                       System.out.println("execute task: " + s);
                   } catch (InterruptedException ignored) {

                   }
               }
            });
            t.start();
            ts.add(t);
        }
        // 2、创建一个add线程，添加是个随机数到队列当中
        Thread add = new Thread(()->{
           for (int i = 0; i < 10; i++) {
               String s = "t-" + Math.random();
               System.out.println("add task: " + s);
               q.addTask(s);
               try {
                   Thread.sleep(100);
               } catch (InterruptedException ignored) {

               }
           }
        });
        add.start();
        add.join();
        Thread.sleep(100);
        for(Thread t : ts) {
            t.interrupt();
        }
    }
}
