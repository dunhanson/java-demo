package site.dunhanson.concurrency.demo.queue;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 订单延期支付，自动取消
 */
@Data
@Slf4j
public class OrderDelayedTask1 implements Delayed {
    private String orderId;
    private long start = System.currentTimeMillis();
    private long time;

    public OrderDelayedTask1(String orderId, long time) {
        this.orderId = orderId;
        this.time = time;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((start + time) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MICROSECONDS) - o.getDelay(TimeUnit.MICROSECONDS));
    }

    public static void main(String[] args) {
        DelayQueue<OrderDelayedTask1> delayQueue = new DelayQueue<>();
        delayQueue.offer(new OrderDelayedTask1("1001", 1000));
        delayQueue.offer(new OrderDelayedTask1("1002", 2000));
        delayQueue.offer(new OrderDelayedTask1("1003", 3000));
        delayQueue.offer(new OrderDelayedTask1("1004", 4000));
        delayQueue.offer(new OrderDelayedTask1("1005", 5000));
        delayQueue.offer(new OrderDelayedTask1("1006", 10000));

        while (true) {
            try {
                OrderDelayedTask1 task = delayQueue.take();
                log.info("date:{}, task:{}", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()), task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
