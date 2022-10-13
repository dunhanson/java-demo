package collection.queue;

import java.util.Deque;
import java.util.LinkedList;

public class DequeExample {
    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<>();
        System.out.println(deque.offerLast("A"));
        System.out.println(deque.offerLast("B"));
        System.out.println(deque.offerFirst("C"));
        System.out.println(deque.pollFirst());
        System.out.println(deque.pollLast());
        System.out.println(deque.pollFirst());
        System.out.println(deque.pollFirst());
    }
}
