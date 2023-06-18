package site.dunhanson.concurrency.demo.single;

import cn.hutool.core.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import site.dunhanson.concurrency.demo.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查询获取数据库10000（1W）条记录
 * @author dunhanson
 * @since 2023-06-18
 * @version 1.0.0
 */
@Slf4j
public class SingleExample1 {
    public static void main(String[] args) {
        int total = 10000;
        int pageSize = 1000;
        int pages = PageUtil.totalPage(total, pageSize);
        long startTimeMillis = System.currentTimeMillis();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for(int pageNo = 1; pageNo <= pages; pageNo ++) {
            int start = PageUtil.getStart(pageNo - 1, pageSize);
            int end = PageUtil.getEnd(pageNo - 1, pageSize);
            System.out.println(start);
            mapList.addAll(CommonUtils.selectListMap("test", start, end));
        }
        long endTimeMillis = System.currentTimeMillis();
        log.info("mapList size:{} 耗时:{}ms", mapList.size(), endTimeMillis - startTimeMillis);
    }
}
