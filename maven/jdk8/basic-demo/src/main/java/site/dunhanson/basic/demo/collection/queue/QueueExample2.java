package site.dunhanson.basic.demo.collection.queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExample2 {

    public static void main(String[] args) {
        Queue<String> q = new LinkedList<>();
        q.offer("apple");
        q.offer("pear");
        q.offer("banana");
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
    }
}
