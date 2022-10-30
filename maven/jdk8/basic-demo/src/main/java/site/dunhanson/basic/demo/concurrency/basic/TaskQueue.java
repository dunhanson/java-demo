package site.dunhanson.basic.demo.concurrency.basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 使用wait和notify
 *
 * <p>TaskQueue
 * <br>1、TaskQueue中包含一个queue集合属性，LinkedList实现类。
 * <br>2、两个方法：addTask、getTask
 * <br>3、addTask(String s)，synchronized修饰，添加完毕后notifyAll
 * <br>4、getTask()，synchronized修饰，内部做queue集合while为空判断，为空调用wait()方法，否则queue remove()后返回
 *
 * <p>main()
 * <br>1、for循环创建5个线程，run方法内部进行执行while(true)，然后调用q.getTask()方法
 * <br>2、创建一个add线程，for循环10次，调用a.addTask(s)方法进行添加随机数
 * <br>3、add线程启动、add线程join，主线程睡眠100毫秒，for循环ts集合中的线程进行interrupt
 *
 * <p>wait()
 * <br>调用wait()方法后，线程进入等待状态，wait()方法不会返回，直到将来某个时刻，线程从等待状态被其他线程唤醒后，wait()方法才会返回，然后，继续执行下一条语句。
 *
 * <p>notify()
 * <br>会唤醒一个正在this锁等待的线程，从而使得等待线程从this.wait()方法返回。
 *
 * @author dunhanson
 * @since 2022-10-30
 */
public class TaskQueue {
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s) {
        this.queue.add(s);
        this.notifyAll();
    }

    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        return queue.remove();
    }


    public static void main(String[] args) throws InterruptedException {
        TaskQueue q = new TaskQueue();
        List<Thread> ts = new ArrayList<>();
        for (int i=0; i<5; i++) {
            Thread t = new Thread(() -> {
                // 执行task:
                while (true) {
                    try {
                        String s = q.getTask();
                        System.out.println("execute task: " + s);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            t.start();
            ts.add(t);
        }
        Thread add = new Thread(() -> {
            for (int i=0; i<10; i++) {
                // 放入task:
                String s = "t-" + Math.random();
                System.out.println("add task: " + s);
                q.addTask(s);
                try { Thread.sleep(100); } catch(InterruptedException ignored) {}
            }
        });
        add.start();
        add.join();
        Thread.sleep(100);
        for (Thread t : ts) {
            t.interrupt();
        }
    }
}
