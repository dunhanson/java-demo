package collection.queue;

import java.time.LocalDate;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueExample {

    public static void main(String[] args) {
        Queue<LocalDate> queue = new PriorityQueue<>();
        queue.add(LocalDate.of(1906, 12, 9));
        queue.add(LocalDate.of(1815, 12, 10));
        queue.add(LocalDate.of(1903, 12, 3));
        queue.add(LocalDate.of(1910, 6, 22));

        System.out.println("Iterating over elements...");
        for (LocalDate date : queue) {
            System.out.println(date);
        }
        System.out.println("Removing elements...");
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }
}
