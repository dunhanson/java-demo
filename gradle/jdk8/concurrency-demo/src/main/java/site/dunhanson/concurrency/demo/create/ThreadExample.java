package site.dunhanson.concurrency.demo.create;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadExample extends Thread {
    @Override
    public void run() {
        log.info("ThreadExample.run");
    }

    public static void main(String[] args) {
        ThreadExample example = new ThreadExample();
        example.start();
    }
}
