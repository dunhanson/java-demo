package site.dunhanson.concurrency.demo.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    static class RelationService implements Runnable {
        private CountDownLatch countDownLatch;

        public RelationService(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "->done.");
            // 当前线程执行结束后进行计数器递减
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new RelationService(countDownLatch), "t1").start();
        new Thread(new RelationService(countDownLatch), "t2").start();
        countDownLatch.await();
        System.out.println("finish.");
    }
}

