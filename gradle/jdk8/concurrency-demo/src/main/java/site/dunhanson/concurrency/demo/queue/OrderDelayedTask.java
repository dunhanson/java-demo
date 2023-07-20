package site.dunhanson.concurrency.demo.queue;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 订单延期支付，自动取消
 */
@Data
@Slf4j
public class OrderDelayedTask implements Delayed {
    private String orderId;
    private long start = System.currentTimeMillis();
    private long time;

    public OrderDelayedTask(String orderId, long time) {
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
        DelayQueue<OrderDelayedTask> delayQueue = new DelayQueue<>();
        delayQueue.offer(new OrderDelayedTask("1001", 1000));
        delayQueue.offer(new OrderDelayedTask("1002", 2000));
        delayQueue.offer(new OrderDelayedTask("1003", 3000));
        delayQueue.offer(new OrderDelayedTask("1004", 4000));
        delayQueue.offer(new OrderDelayedTask("1005", 5000));
        delayQueue.offer(new OrderDelayedTask("1006", 6000));

        while (true) {
            try {
                OrderDelayedTask task = delayQueue.take();
                log.info("{}", task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
