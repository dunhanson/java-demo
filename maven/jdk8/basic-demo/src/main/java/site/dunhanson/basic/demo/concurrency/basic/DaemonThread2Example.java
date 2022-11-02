package site.dunhanson.basic.demo.concurrency.basic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * 守护线程
 * <p>当 JVM 中不存在任何一个正在运行的非守护线程时，则 JVM 进程即会退出。<br>
 * @author dunhanson
 * @since 2022-11-02
 */
public class DaemonThread2Example {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("I am running ..." + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("The main thread ready to exit.");
    }
}
