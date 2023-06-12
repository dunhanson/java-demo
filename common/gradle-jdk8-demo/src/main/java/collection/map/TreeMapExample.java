package collection.map;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        Map<String, Object> map = new TreeMap<>();
        map.put("b", "bbb");
        map.put("c", "ccc");
        map.put("a", "aaa");

        System.out.println(map);
    }
}
