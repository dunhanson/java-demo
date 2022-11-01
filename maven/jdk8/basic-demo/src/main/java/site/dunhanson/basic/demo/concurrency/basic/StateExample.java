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
    private synchronized void say() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " say hello");
        try {
            Thread.sleep(1000);
            if(name.contains("Thread-0")) {
                this.wait();
            }
            if(name.contains("Thread-1")) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        StateExample example = new StateExample();
        Thread t0 = new Thread(example::say);
        Thread t1 = new Thread(example::say);
        System.out.println("1:" + t0.getState());
        t0.start();
        t1.start();
        System.out.println("2:" + t1.getState());
        System.out.println("3:" + t0.getState());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("4:" + t1.getState());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("5:" + t0.getState());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("6:" + t1.getState());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("7:" + t1.getState());
    }
}
