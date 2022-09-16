package site.dunhanson.concurrency.demo.basic;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 串行和并行示例
 */
public class SerialAndParallelExample {

    /**
     * 串行
     * @throws InterruptedException 线程打断异常
     */
    public void serial() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int count = 0;
        for(int i = 1; i <= 10; i++) {
            if(i % 2 == 0) {
                doSomething(i);
            }
            count += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println(count);
        System.out.println("耗时:" + (endTime - startTime));
    }

    /**
     * 并行
     * @throws InterruptedException 线程打断异常
     */
    public void parallel() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(8);
        AtomicInteger count = new AtomicInteger();
        for(int i = 1; i <= 10; i++) {
            int finalI = i;
            service.submit(()-> {
                try {
                    doSomething(finalI);
                    return count.addAndGet(finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        service.shutdown();
        while (true) {
            if(service.awaitTermination(5, TimeUnit.MILLISECONDS)) {
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(count);
        System.out.println("耗时:" + (endTime - startTime));
    }

    /**
     * 模拟任务处理
     * @param i 参数i
     * @throws InterruptedException 线程打断异常
     */
    public void doSomething(int i) throws InterruptedException {
        if(i % 2 == 0) {
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SerialAndParallelExample example = new SerialAndParallelExample();
        example.serial();
        example.parallel();
    }
}
