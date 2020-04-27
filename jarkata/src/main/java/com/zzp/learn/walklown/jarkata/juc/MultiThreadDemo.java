package com.zzp.learn.walklown.jarkata.juc;

import java.util.concurrent.*;

/**
 * 线程
 * < Java 5: Thread, Runable
 * Java 5: Executor, Future, Callable
 * Java 7: ForkJoin
 * Java 8: CompletionStage, CompletableFuture
 * Java 9: Flow(Publisher, Subscriber, Subscription, Processor)
 *
 * @author shoujing
 * @date 2019/12/24 22:40
 */
public class MultiThreadDemo {

    // RUNNING    11100000000000000000000000000000 -1
    // SHUTDOWN   0                                 0
    // STOP       00100000000000000000000000000000  1
    // TIDYING    01000000000000000000000000000000  2
    // TERMINATED 01100000000000000000000000000000  3
    private static final ExecutorService executor = new ThreadPoolExecutor(1, 2,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Java 5
        executorTest();
//        forkJoinTest();
//        int s = Integer.SIZE - 3;
//        System.out.println(Integer.toBinaryString(-1 << s));
//        System.out.println(Integer.toBinaryString(0 << s));
//        System.out.println(Integer.toBinaryString(1 << s));
//        System.out.println(Integer.toBinaryString(2 << s));
//        System.out.println(Integer.toBinaryString(3 << s));
    }

    //Java 5
    /**
     * java.util.concurrent.ThreadPoolExecutor#execute(java.lang.Runnable)
     * 共三步：
     * 1、 如果当前线程数小于 core 线程数，尝试为 Task 开启新的核心线程。成功直接返回，失败
     * 往下进行。
     * 2、 如果当前线程池状态是 RUNNING，尝试加入待执行 Queue，成功则再次检查线程池状态，不
     * 是 RUNNING 就回滚（remove 当前任务），并执行 reject。是 RUNNING 或者回滚失败，且
     * 当前已经没有工作线程，就开启一个非核心线程。
     * 3、 对于非 RUNNING 的线程池，尝试为 Task 开启一个非核心线程池，如果失败，就执行
     * reject。
     */
    public static void executorTest() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            System.out.println("Execute Callable");
            Thread.sleep(3000 * 1000);
            return 0;
        };
        executor.execute(() -> {
            System.out.println("Execute Runable1");
            try {
                Thread.sleep(3000 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            System.out.println("Execute Runable2");
            try {
                Thread.sleep(3000 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        Future future1 = executor.submit(callable);
//        future1.get();
    }

    public static void forkJoinTest() {
        StringForkJoinTask forkJoinTask = new StringForkJoinTask(0, 10);
        Future<String> result = ForkJoinPool.commonPool().submit(forkJoinTask);
        try {
            System.out.println(String.format("%s-%s", Thread.currentThread().getName(), result.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

class StringForkJoinTask extends RecursiveTask<String> {

    private final int from;

    private final int to;

    public StringForkJoinTask(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected String compute() {
        System.out.println(Thread.currentThread().getName());
        if (from < 2 && to < 9) {
            return "0";
        }
        StringForkJoinTask left = new StringForkJoinTask(from, (to - from) / 2);
        StringForkJoinTask right = new StringForkJoinTask((to - from) / 2 + 1, to);
        left.fork();
        right.fork();
        left.join();
        right.join();
        return Integer.valueOf(from + to).toString();
    }
}
