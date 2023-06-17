package site.dunhanson.concurrency.demo.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

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
            5,
            new CustomNameThreadFactory("A")
    );

    /**
     * 自定义线程名ThreadFactory
     */
    static class CustomNameThreadFactory implements ThreadFactory {
        private final String threadNamePrefix;

        public CustomNameThreadFactory(String threadNamePrefix) {
            this.threadNamePrefix = threadNamePrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(this.threadNamePrefix + "-" + thread.getId());
            return thread;
        }
    }

}
