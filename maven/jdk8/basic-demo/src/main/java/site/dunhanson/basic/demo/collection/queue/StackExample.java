package site.dunhanson.basic.demo.collection.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 栈（Stack）是一种后进先出（LIFO：Last In First Out）的数据结构。
 * @author dunhanson
 * @since 2022-10-18
 */
public class StackExample {
    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<>();
        deque.push("a");
        deque.push("b");
        deque.push("c");
        System.out.println(deque.pop());
        System.out.println(deque.pop());
        System.out.println(deque.pop());
    }
}
