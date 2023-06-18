package site.dunhanson.concurrency.demo.common;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * 自定义线程名ForkJoinWorkerThreadFactory
 * @author dunhanson
 * @since 2023-06-17
 * @version 1.0.0
 */
public class CustomNameForkJoinWorkerThreadFactory implements ForkJoinPool.ForkJoinWorkerThreadFactory {
    private final String threadNamePrefix;

    public CustomNameForkJoinWorkerThreadFactory(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
        return new ForkJoinWorkerThread(pool) {
            @Override
            protected void onStart() {
                super.onStart();
                this.setName(threadNamePrefix + "-" + this.getId());
            }
        };
    }
}
