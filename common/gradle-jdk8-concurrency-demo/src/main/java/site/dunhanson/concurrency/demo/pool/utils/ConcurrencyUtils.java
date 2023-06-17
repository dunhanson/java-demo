package site.dunhanson.concurrency.demo.pool.utils;

import site.dunhanson.concurrency.demo.pool.common.CustomNameThreadFactory;

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
            5,
            new CustomNameThreadFactory("A")
    );

}
