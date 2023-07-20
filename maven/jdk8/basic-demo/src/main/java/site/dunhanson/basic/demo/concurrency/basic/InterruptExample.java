package site.dunhanson.basic.demo.concurrency.basic;

/**
 * 线程中断
 * <p>如果线程需要执行一个长时间任务，就可能需要能中断线程。<br>
 * 中断线程就是其他线程给该线程发一个信号，该线程收到信号后结束执行run()方法，使得自身线程能立刻结束运行。<br>
 * @author dunhanson
 * @since 2022-11-02
 */
public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread();
        t.start();
        // 主线程睡眠1毫秒，t线程执行一段时间
        Thread.sleep(1);
        t.interrupt();
        t.join();
        System.out.println("end");
    }

    private static class MyThread extends Thread {

        /**
         * 如果线程没有被打断，就一直while执行打印hello
         */
        @Override
        public void run() {
            int n = 0;
            while (!this.isInterrupted()) {
                n++;
                System.out.println(n + " hello!");
            }
        }

    }
}
