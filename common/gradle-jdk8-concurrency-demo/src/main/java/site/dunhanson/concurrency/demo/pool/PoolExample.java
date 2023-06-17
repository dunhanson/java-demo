package site.dunhanson.concurrency.demo.pool;

import lombok.extern.slf4j.Slf4j;
import site.dunhanson.concurrency.demo.pool.utils.ConcurrencyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Slf4j
public class PoolExample {

    /**
     * 线程池使用的简单例子
     * <p>1、从工具类中获取线程池（进行复用，每个业务单独设置一个专属的线程池）</p>
     * <p>2、创建一个专门用来存放Future的集合</p>
     * <p>3、线程池运行任务，把Future添加到List集合中</p>
     * <p>4、统计执行成功的数量</p>
     * @param args 参数
     */
    public static void main(String[] args) {
        // 1、从工具类中获取线程池（进行复用，每个业务单独设置一个专属的线程池）
        // 如果你的Java项目中有多个场景需要使用线程池，那么最好为每一个业务场景使用独立的线程池，不要让所有的场景共用一个线程池。
        ExecutorService executorService = ConcurrencyUtils.EXECUTOR_SERVICE_A;

        // 2、创建一个专门用来存放Future的集合
        List<Future<Boolean>> submitResultList = new ArrayList<>();

        // 3、线程池运行任务，把Future添加到List集合中
        // 方法1
        Future<Boolean> submit1 = executorService.submit(() -> {
            print1();
            return true;
        });
        submitResultList.add(submit1);
        // 方法2
        Future<Boolean> submit2 = executorService.submit(()->{
            print2();
            return true;
        });
        submitResultList.add(submit2);
        // 方法3
        Future<Boolean> submit3 = executorService.submit(()->{
            print3();
            return true;
        });
        submitResultList.add(submit3);

        // 4、统计执行成功的数量
        long allCount = submitResultList.size();
        long successCount = submitResultList.stream().map(submit -> {
            try {
                return submit.get();
            } catch (Exception e) {
                log.warn("submit get fail:{}", e.getMessage());
                Thread.currentThread().interrupt();
            }
            return false;
        }).filter(Boolean::booleanValue).count();
        long failCount = allCount - successCount;
        log.info("执行的任务数量:{}, 执行成功的数量:{}, 执行失败的数量:{}", allCount, successCount, failCount);

        // 5、关闭线程池（实际场景，没有这一步）
        executorService.shutdown();
        while (true) {
            if(executorService.isTerminated()) {
                break;
            }
        }
        log.info("finish.");
    }

    public static void print1() {
        log.info("print1");
    }

    public static void print2() {
        log.info("print2");
    }

    public static void print3() {
        log.info("print3");
    }
}
