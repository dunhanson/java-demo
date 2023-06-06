package collection.queue;

import java.time.LocalDate;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueExample1 {

    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(9);
        queue.add(10);
        queue.add(3);
        queue.add(22);

        System.out.println("Iterating over elements...");
        for (Integer integer : queue) {
            System.out.println(integer);
        }
        System.out.println("Poll elements...");
        for(int i = 0; i < 4; i++) {
            System.out.println(queue.poll());
        }
        System.out.println("Removing elements...");
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }
}
