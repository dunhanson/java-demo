package site.dunhanson.basic.demo.concurrency.basic;

/**
 * 可重入的锁
 * <p>JVM允许同一个线程重复获取同一个锁，这种能被同一个线程反复获取的锁，就叫做可重入锁。
 * @author dunhanson
 * @since 2022-11-02
 */
public class ReentrantLockExample {
    private static class Counter {
        private int count = 0;

        public synchronized void add(int n) {
            if(n < 0) {
                dec(-n);
            } else {
                count += n;
            }
        }

        public synchronized void dec(int n) {
            count +=n;
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        counter.add(-1);
        System.out.println(counter.count);
    }
}
