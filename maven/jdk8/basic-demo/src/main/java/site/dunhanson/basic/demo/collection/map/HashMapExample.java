package site.dunhanson.basic.demo.collection.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "aaa");
        map.put("b", "bbb");
        map.put("c", "ccc");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        map.hashCode();
    }
}
