package collection;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class SetTest {
    /**
     * 基于红黑树实现，支持有序性操作
     * 查找效率不如HashSet
     */
    @Test
    public void testTreeSet() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(3);
        set.add(2);
        set.add(4);
        set.add(5);
        set.forEach(System.out::println);
    }

    /**
     * 基于哈希表，支持快速查找，不支持有序性
     * 失去了元素的插入顺序信息，遍历元素时结果是不确定的
     */
    @Test
    public void testHashSet() {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(3);
        set.add(2);
        set.add(4);
        set.add(5);
        set.forEach(System.out::println);
    }

    /**
     * 具有HashSet的查询效率
     * 内部使用双向链表维护元素的插入顺序
     */
    @Test
    public void testLinkedHashSet() {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        set.add(1);
        set.add(3);
        set.add(2);
        set.add(4);
        set.add(5);
        set.forEach(System.out::println);
    }

}
