package site.dunhanson.concurrency.demo.utils;

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
     * 获取自定义名称的Executor
     * @param name 线程名称
     * @param threads 线程数量
     * @return Executor
     */
    public static ExecutorService getCustomNameExecutor(String name, int threads)  {
        return Executors.newFixedThreadPool(
                threads,
                new CustomNameThreadFactory(name)
        );
    }


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
