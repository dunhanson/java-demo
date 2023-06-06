package site.dunhanson.concurrency.demo.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 生产者/消费者模式代码
 * 1、生产者循环100次，往阻塞队列中添加数据，添加后打印线程名，阻塞队列大小
 * 2、消费者循环100次，从阻塞队列中获取数据，获取后打印线程名，阻塞队列大小
 * 3、初始化阻塞队列，启动生产者、睡眠10毫秒（保证生产者先执行）、启动消费者
 */
@Slf4j
public class ProducerAndConsumerProgram {

    /**
     * 生产者
     */
    static class Producer implements Runnable{
        BlockingDeque<String> blockingDeque;

        public Producer(BlockingDeque<String> blockingDeque) {
            this.blockingDeque = blockingDeque;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    blockingDeque.put("element:" + i);
                    log.info("{}，生产者生产数据，目前总共的元素个数：{}", Thread.currentThread().getName(), blockingDeque.size());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 消费者
     */
    static class Consumer implements Runnable {
        BlockingDeque<String> blockingDeque;

        public Consumer(BlockingDeque<String> blockingDeque) {
            this.blockingDeque = blockingDeque;
        }

        @Override
        public void run() {
            for(int i = 0; i < 100; i++) {
                try {
                    blockingDeque.take();
                    log.info("{}，消费者消费数据，目前总共的元素个数：{}", Thread.currentThread().getName(), blockingDeque.size());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>(10);
        Producer producer = new Producer(blockingDeque);
        Consumer consumer = new Consumer(blockingDeque);
        new Thread(producer).start();
        TimeUnit.SECONDS.sleep(10);
        new Thread(consumer).start();
    }

}
