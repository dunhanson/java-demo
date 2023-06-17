package site.dunhanson.concurrency.demo.pool.common;

import java.util.concurrent.ThreadFactory;

/**
 * 自定义线程名ThreadFactory
 * @author dunhanson
 * @since 2023-06-17
 * @version 1.0.0
 */
public class CustomNameThreadFactory implements ThreadFactory {
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