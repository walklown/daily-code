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
        executorTest();
        forkJoinTest();
    }

    //Java 5
    public static void executorTest() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            System.out.println("Execute Callable");
            Thread.sleep(3000);
            return 0;
        };
        executor.execute(()->{
            System.out.println("Execute Runable");
        });
        Future future1 = executor.submit(callable);
        future1.get();
    }

    private static ForkJoinTask forkJoinTask = new ForkJoinTask() {
        @Override
        public Object getRawResult() {
            return null;
        }

        @Override
        protected void setRawResult(Object value) {

        }

        @Override
        protected boolean exec() {
            return false;
        }
    };

    public static void forkJoinTest() {
        ForkJoinPool.commonPool().execute(forkJoinTask);
        forkJoinTask.invoke();
    }

}
