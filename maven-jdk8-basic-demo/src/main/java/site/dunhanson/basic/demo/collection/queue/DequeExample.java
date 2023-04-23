package site.dunhanson.basic.demo.collection.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 允许两头都进，两头都出，这种队列叫双端队列（Double Ended Queue），学名Deque
 * <p>既可以添加到队尾，也可以添加到队首<br>
 * 既可以从队首获取，又可以从队尾获取
 * @author dunhanson
 * @since 2022-10-18
 */
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
