package site.dunhanson.concurrency.demo.common.utils;

import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用工具类
 * @author dunhanson
 * @since 2023-06-16
 * @version 1.0.0
 */
@Slf4j
public class CommonUtils {

    /**
     * 查询ListMap集合
     * @param table 表名
     * @param start 开始记录数
     * @param end 结束记录数
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> selectListMap(String table, int start, int end) {
        log.info("正在获取表数据:{}, 分页参数start:{},end:{}", table, start, end);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for(; start < end; start++) {
            Map<String, Object> map = new HashMap<>();
            map.put(String.valueOf(start), String.valueOf(start) + start + start);
            list.add(map);
        }
        return list;
    }
}
