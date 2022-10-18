package site.dunhanson.basic.demo.collection.queue;

import java.util.Deque;
import java.util.LinkedList;

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
