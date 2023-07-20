package site.dunhanson.basic.demo.collection.queue;

import lombok.Data;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * PriorityQueue和Queue的区别在于，它的出队顺序与元素的优先级有关，
 * @author dunhanson
 * @since 2022-10-18
 */
public class PriorityQueueExample {

    @Data
    static class User {
        public final String name;
        public final String number;

        public User(String name, String number) {
            this.name = name;
            this.number = number;
        }

        public String toString() {
            return name + "/" + number;
        }
    }

    public void testString() {
        Queue<String> q = new PriorityQueue<>();
        // 添加3个元素到队列:
        q.offer("apple");
        q.offer("pear");
        q.offer("banana");
        System.out.println(q.poll()); // apple
        System.out.println(q.poll()); // banana
        System.out.println(q.poll()); // pear
        System.out.println(q.poll()); // null,因为队列为空
    }

    @Data
    static class UserComparator implements Comparator<User> {

        @Override
        public int compare(User u1, User u2) {
            if(u1.number.charAt(0) == u2.number.charAt(0)) {
                return u1.number.compareTo(u2.number);
            }
            if(u1.number.charAt(0) == 'V') {
                return -1;
            }
            return 1;
        }
    }

    public void testUser() {
        Queue<User> q = new PriorityQueue<>(new UserComparator());
        q.offer(new User("Bob", "A1"));
        q.offer(new User("Alice", "A2"));
        q.offer(new User("Boss", "V1"));
        System.out.println(q.poll()); // Boss/V1
        System.out.println(q.poll()); // Bob/A1
        System.out.println(q.poll()); // Alice/A2
        System.out.println(q.poll()); // null,因为队列为空
    }

    public static void main(String[] args) {
        PriorityQueueExample example = new PriorityQueueExample();
        example.testUser();
    }
}
