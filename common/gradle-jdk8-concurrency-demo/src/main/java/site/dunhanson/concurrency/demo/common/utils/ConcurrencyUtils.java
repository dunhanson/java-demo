package site.dunhanson.concurrency.demo.common.utils;

import site.dunhanson.concurrency.demo.common.CustomNameThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池工具类
 * @author dunhanson
 * @since 2023-06-17
 * @version 1.0.0
 */
public class ConcurrencyUtils {
    private ConcurrencyUtils() {

    }

    /**
     * 线程池常量，试用场景：A业务
     */
    public static final ExecutorService EXECUTOR_SERVICE_A = Executors.newFixedThreadPool(
            10,
            new CustomNameThreadFactory("A")
    );

    /**
     * 关闭ExecutorService
     * <p>实际场景一般不关闭</p>
     * @param executorService ExecutorService
     */
    public static void stopExecutorService(ExecutorService executorService) {
        executorService.shutdown();
        while (true) {
            if(executorService.isTerminated()) {
                break;
            }
        }
    }

}
