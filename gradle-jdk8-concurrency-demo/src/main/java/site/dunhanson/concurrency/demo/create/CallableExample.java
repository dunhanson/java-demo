package site.dunhanson.concurrency.demo.create;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class CallableExample implements Callable<String> {
    @Override
    public String call() {
        return "执行结果:SUCCESS";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableExample callableExample = new CallableExample();
        FutureTask<String> futureTask = new FutureTask<>(callableExample);
        Thread thread = new Thread(futureTask);
        thread.start();
        log.info("result:{}", futureTask.get());
    }
}
