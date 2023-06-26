package site.dunhanson.concurrency.demo.parallel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import site.dunhanson.concurrency.demo.utils.CommonUtils;
import site.dunhanson.concurrency.demo.common.CustomNameForkJoinWorkerThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 查询获取数据库10000（1W）条记录
 * @author dunhanson
 * @since 2023-06-16
 * @version 1.0.0
 */
@Slf4j
public class ForkJoinExample1 {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(10,
                new CustomNameForkJoinWorkerThreadFactory("batch query database"),
                null,
                false);
        long start = System.currentTimeMillis();
        List<Map<String, Object>> mapList = pool.invoke(
                new BatchQueryDatabaseRecursiveTask("test", 0, 10000, 1000)
        );
        long end = System.currentTimeMillis();
        log.info("mapList size:{} 耗时:{}ms", mapList.size(), end - start);
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    static class BatchQueryDatabaseRecursiveTask extends RecursiveTask<List<Map<String, Object>>> {

        private String table;
        private Integer start;
        private Integer end;
        private Integer limit;


        public BatchQueryDatabaseRecursiveTask(String table, Integer start, Integer end, Integer limit) {
            this.table = table;
            this.start = start;
            this.end = end;
            this.limit = limit;
        }

        @Override
        protected List<Map<String, Object>> compute() {
            if(end - start <= limit) {
                return CommonUtils.selectListMap(this.table, this.start, this.end);
            }

            int middle = (start + end) / 2;
            BatchQueryDatabaseRecursiveTask left = new BatchQueryDatabaseRecursiveTask(table, start, middle, limit);
            BatchQueryDatabaseRecursiveTask right = new BatchQueryDatabaseRecursiveTask(table, middle, end, limit);

            left.fork();
            right.fork();

            List<Map<String, Object>> mapList = new ArrayList<>();
            mapList.addAll(left.join());
            mapList.addAll(right.join());
            return mapList;
        }
    }
}
