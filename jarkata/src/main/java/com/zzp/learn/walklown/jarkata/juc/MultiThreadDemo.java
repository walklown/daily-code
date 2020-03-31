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

    private static final ExecutorService executor = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Java 5
//        executorTest();
        forkJoinTest();
//        int s = Integer.SIZE - 3;
//        System.out.println(Integer.toBinaryString(-1 << s));
//        System.out.println(Integer.toBinaryString(0 << s));
//        System.out.println(Integer.toBinaryString(1 << s));
//        System.out.println(Integer.toBinaryString(2 << s));
//        System.out.println(Integer.toBinaryString(3 << s));
    }

    //Java 5
    public static void executorTest() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            System.out.println("Execute Callable");
            Thread.sleep(3000);
            return 0;
        };
        executor.execute(() -> {
            System.out.println("Execute Runable");
        });
        Future future1 = executor.submit(callable);
        future1.get();
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
