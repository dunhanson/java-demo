package site.dunhanson.concurrency.demo.basic;

import cn.hutool.core.thread.ThreadUtil;

/**
 * 线程状态例子
 * @author dunhanson
 * @since 2023-06-19
 * @version 1.0.0
 */
public class ThreadStateExample {
    public static void main(String[] args) {
        ThreadStateExample.stateNew();
        // 主线程睡眠
        ThreadUtil.safeSleep(1000 * 60 * 60);
    }

    /**
     * 新建状态
     */
    public static void stateNew() {
        Runnable runnable = () -> {

        };
        Thread thread = new Thread(runnable);
        thread.setName("stateNew");
        //thread.start();
    }
}
