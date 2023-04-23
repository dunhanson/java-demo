package site.dunhanson.basic.demo.concurrency.basic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future
 * <p>当我们提交一个Callable任务后，我们会同时获得一个Future对象<br>
 * 然后，我们在主线程某个时刻调用Future对象的get()方法，就可以获得异步执行的结果。<br>
 * 在调用get()时，如果异步任务已经完成，我们就直接获得结果。如果异步任务还没有完成，那么get()会阻塞，直到任务完成后才返回结果。
 * @author dunhanson
 * @since 2022-10-31
 */
public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(() -> {
            Thread.sleep(1000 * 5);
            return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + " " + "hello,world";
        });
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(future.get());
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
    }
}
