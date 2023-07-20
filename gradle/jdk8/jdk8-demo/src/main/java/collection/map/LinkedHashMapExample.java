package collection.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("b", "bbb");
        map.put("c", "ccc");
        map.put("a", "aaa");
        System.out.println(map);
        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            System.out.println(stringObjectEntry);
        }
    }
}
