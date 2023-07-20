package site.dunhanson.concurrency.demo.basic;

public class AtomicExample {
    volatile int i = 0;

    public void incr() {
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicExample atomicExample = new AtomicExample();
        Thread[] threads = new Thread[2];
        for(int i = 0; i < 2; i++) {
            threads[i] = new Thread(()->{
                for(int j = 0; j < 10000; j++) {
                    atomicExample.incr();
                }
            });
            threads[i].start();
        }
        threads[0].join();
        threads[1].join();
        System.out.println(atomicExample.i);
    }
}
