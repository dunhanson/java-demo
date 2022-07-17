package site.dunhanson.concurrency.demo.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptExample extends Thread{
    @Override
    public void run() {
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            i++;
        }
        System.out.println("线程已被中断,i=" + i);
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptExample example = new InterruptExample();
        example.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("before:InterruptExample 中断状态:" + example.isInterrupted());
        example.interrupt();
        System.out.println("after:InterruptExample 中断状态:" + example.isInterrupted());
    }
}
