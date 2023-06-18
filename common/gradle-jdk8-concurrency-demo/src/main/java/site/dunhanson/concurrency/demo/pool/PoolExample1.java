package site.dunhanson.concurrency.demo.pool;

import cn.hutool.core.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import site.dunhanson.concurrency.demo.utils.CommonUtils;
import site.dunhanson.concurrency.demo.utils.ConcurrencyUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * 查询获取数据库10000（1W）条记录
 * @author dunhanson
 * @since 2023-06-18
 * @version 1.0.0
 */
@Slf4j
public class PoolExample1 {
    public static void main(String[] args) {
        ExecutorService executorService = ConcurrencyUtils.getCustomNameExecutor(
                PoolExample1.class.getName()
        );
        long startTimeMillis = System.currentTimeMillis();
        int total = 10000;
        int pageSize = 1000;
        int pages = PageUtil.totalPage(total, pageSize);
        List<Future<List<Map<String, Object>>>> futureList = new ArrayList<>();
        for(int pageNo = 1; pageNo <= pages; pageNo ++) {
            int start = PageUtil.getStart(pageNo - 1, pageSize);
            int end = PageUtil.getEnd(pageNo - 1, pageSize);
            System.out.println(start);
            futureList.add(executorService.submit(()-> CommonUtils.selectListMap("test", start, end)));
        }
        List<Map<String, Object>> mapList = futureList.stream().map(future -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).flatMap(Collection::stream).collect(Collectors.toList());
        long endTimeMillis = System.currentTimeMillis();
        log.info("mapList size:{} 耗时:{}ms", mapList.size(), endTimeMillis - startTimeMillis);
        // 5、关闭线程池（实际场景，没有这一步）
        executorService.shutdown();
        while (true) {
            if(executorService.isTerminated()) {
                break;
            }
        }
        log.info("finish.");
    }
}
