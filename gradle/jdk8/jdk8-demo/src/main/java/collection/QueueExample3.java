package collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExample3 {

    public static void main(String[] args) {
        Queue<String> q = new LinkedList<>();
        q.offer("apple");
        q.offer("pear");
        q.offer("banana");
        System.out.println(q.peek());
        System.out.println(q.peek());
        System.out.println(q.peek());
    }
}
