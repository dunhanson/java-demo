package site.dunhanson.concurrency.demo.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * JDK8 fork join例子
 * <p>JDK 8中的Fork/Join框架是一个用于并行处理任务的框架。它的核心思想是将一个大任务分割成若干个小任务，
 * 然后将这些小任务分配给多个线程去执行，最后将这些小任务的结果合并起来得到最终结果。</p>
 * <p>Fork/Join框架的核心类是ForkJoinPool。ForkJoinPool是一个线程池，它可以执行ForkJoinTask任务。
 * ForkJoinTask是一个抽象类，它有两个子类：RecursiveAction和RecursiveTask。
 * RecursiveAction表示没有返回值的任务，RecursiveTask表示有返回值的任务。</p>
 * <p>ForkJoinPool的执行过程如下：</p>
 * <p>1.将一个大任务分割成若干个小任务，这些小任务可以并行执行。</p>
 * <p>2.将这些小任务提交给ForkJoinPool。</p>
 * <p>3.ForkJoinPool将这些小任务分配给多个线程去执行。</p>
 * <p>4.线程执行任务，如果任务太大，就将任务继续分割成若干个小任务，然后将这些小任务提交给ForkJoinPool。</p>
 * <p>5.线程执行完任务后，将结果返回给ForkJoinPool。</p>
 * <p>6.ForkJoinPool将所有结果合并起来，得到最终结果。</p>
 * @author dunhanson
 * @since 2023-06-16
 * @version 1.0.0
 */
public class ForkJoinExample {

    /**
     * 在这个例子中，我们创建了一个ForkJoinPool，并使用它来计算一个整数数组的总和。
     * 我们定义了一个SumTask类，它继承了RecursiveTask类，并实现了compute()方法。
     * 在compute()方法中，我们检查数组的大小是否小于等于2，如果是，则直接计算数组的总和。
     * 否则，我们将数组分成两个部分，并创建两个SumTask对象来计算每个部分的总和。我们使用fork()方法
     * @param args 参数
     */
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int sum = forkJoinPool.invoke(new SumTask(array, 0, array.length));

        System.out.println("Sum: " + sum);
    }

    private static class SumTask extends RecursiveTask<Integer> {
        private final int[] array;
        private final int start;
        private final int end;

        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= 2) {
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            } else {
                int mid = start + (end - start) / 2;
                SumTask leftTask = new SumTask(array, start, mid);
                SumTask rightTask = new SumTask(array, mid, end);
                leftTask.fork();
                int rightSum = rightTask.compute();
                int leftSum = leftTask.join();
                return leftSum + rightSum;
            }
        }
    }
}
