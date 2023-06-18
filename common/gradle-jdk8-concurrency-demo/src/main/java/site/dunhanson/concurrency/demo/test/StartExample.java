package site.dunhanson.concurrency.demo.test;

import lombok.extern.slf4j.Slf4j;
import site.dunhanson.concurrency.demo.pool.PoolExample1;
import site.dunhanson.concurrency.demo.utils.CommonUtils;
import site.dunhanson.concurrency.demo.utils.ConcurrencyUtils;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Slf4j
public class StartExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU 核心数：" + coreCount);

        ExecutorService executorService1 = ConcurrencyUtils.getCustomNameExecutor(
                PoolExample1.class.getName()
        );
        ExecutorService executorService2 = ConcurrencyUtils.getCustomNameExecutor(
                PoolExample1.class.getName(),
                10
        );

        Future<?> f1 = executorService1.submit(() -> log.info("executorService1"));
        Future<?> f2 = executorService2.submit(()-> log.info("executorService2"));

        f1.get();
        f2.get();

        ConcurrencyUtils.stopExecutorService(executorService1);
        ConcurrencyUtils.stopExecutorService(executorService2);
        CommonUtils.finish();
    }
}
