package site.dunhanson.concurrency.demo.create;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnableThreadExample extends OtherClass implements Runnable{
    @Override
    public void run() {
        log.info("RunnableThreadExample.run");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableThreadExample());
        thread.start();
    }
}
