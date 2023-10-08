package site.dunhanson.hutool;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 类描述
 *
 * @author dunhanson
 * @version 1.0.0
 * @since 2023-10-08
 */
@Slf4j
public class HutoolJsonDemo1 {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        log.info(JSONUtil.toJsonPrettyStr(map));
    }
}
