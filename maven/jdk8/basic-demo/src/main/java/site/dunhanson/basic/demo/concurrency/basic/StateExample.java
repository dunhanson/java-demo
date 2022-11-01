package site.dunhanson.basic.demo.concurrency.basic;

/**
 * 线程状态
 * New：新创建的线程，尚未执行；
 * Runnable：运行中的线程，正在执行run()方法的Java代码；
 * Blocked：运行中的线程，因为某些操作被阻塞而挂起；
 * Waiting：运行中的线程，因为某些操作在等待中；
 * Timed Waiting：运行中的线程，因为执行sleep()方法正在计时等待；
 * Terminated：线程已终止，因为run()方法执行完毕。
 * @author dunhanson
 * @since 2022-11-01
 */
public class StateExample {

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private synchronized void say() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " say hello");
        try {
            Thread.sleep(1000);
            if(name.contains("Thread-0")) {
                this.wait();
            }
            this.notifyAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StateExample example = new StateExample();
        Thread t0 = new Thread(example::say);
        Thread t1 = new Thread(example::say);
        // 1:NEW
        System.out.println("1:" + t0.getState());
        t0.start();
        t1.start();
        // 2:RUNNABLE
        System.out.println("2:" + t1.getState());
        // 3:BLOCKED
        StateExample.sleep(100);
        System.out.println("3:" + t1.getState());
        // 4:WAITING
        StateExample.sleep(900);
        System.out.println("4:" + t0.getState());
        // 5:TIMED_WAITING
        System.out.println("5:" + t1.getState());
        // 6:TERMINATED
        StateExample.sleep(1000);
        System.out.println("6:" + t1.getState());
        t0.join();
        t1.join();
        System.out.println("finish.");
    }
}
