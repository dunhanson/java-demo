package site.dunhanson.concurrency.demo.pool;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个有固定数量的线程池
 */
public class FixedThreadPoolExample {
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();
        CountDownLatch countDownLatch = new CountDownLatch(4);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Integer> projects = Collections.synchronizedList(new ArrayList<Integer>());
        executorService.submit(() -> {
            List<Project> response1 = projectService.findProjectByOwnerEnterpriseReportResponse(response, Arrays.asList(201L, 225L));
            projects.addAll(response1);
            countDownLatch.countDown();
        }, countDownLatch);
        executorService.submit(() -> {
            List<Project> response2 = projectService.findProjectByOwnerEnterpriseReportResponse(response, Arrays.asList(226L, 250L));
            projects.addAll(response2);
            countDownLatch.countDown();
        }, countDownLatch);
        executorService.submit(() -> {
            List<Project> response3 = projectService.findProjectByOwnerEnterpriseReportResponse(response, Arrays.asList(251L, 275L));
            projects.addAll(response3);
            countDownLatch.countDown();
        }, countDownLatch);
        executorService.submit(() -> {
            List<Project> response4 = projectService.findProjectByOwnerEnterpriseReportResponse(response, Arrays.asList(276L, 300L));
            projects.addAll(response4);
            countDownLatch.countDown();
        }, countDownLatch);

        LocalDateTime.now();
        countDownLatch.countDown();
        executorService.shutdown();
        System.out.println("项目初始化结果-----------》" + projects);
        System.out.println("耗时-------》" + Duration.between(start, LocalDateTime.now()).getSeconds());
    }
}
