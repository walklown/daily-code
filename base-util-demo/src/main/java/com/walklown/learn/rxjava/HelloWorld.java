package com.walklown.learn.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

import java.util.concurrent.TimeUnit;

public class HelloWorld {
    public static void main(String[] args) throws InterruptedException {
        replaySubject();
    }

    public static void flowable1() throws InterruptedException {
        Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);

        Thread.sleep(2000); // <--- wait for the flow to finish
    }

    public static void flowable2() throws InterruptedException {
        Flowable<String> source = Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        });

        Flowable<String> runBackground = source.subscribeOn(Schedulers.io());

        Flowable<String> showForeground = runBackground.observeOn(Schedulers.single());

        showForeground.subscribe(System.out::println, Throwable::printStackTrace);

        Thread.sleep(2000);
    }

    public static void subject() {
        PublishSubject<Integer> subject = PublishSubject.create();
        subject.onNext(1);
        subject.subscribe(System.out::println);
        subject.onNext(2);
        subject.onNext(3);
        subject.onNext(4);
    }

    public static void replaySubject() throws InterruptedException {
        ReplaySubject<Integer> s = ReplaySubject.createWithTime(150, TimeUnit.MILLISECONDS,
                Schedulers.io());
        s.onNext(0);
        Thread.sleep(100);
        s.onNext(1);
        Thread.sleep(100);
        s.onNext(2);
        s.subscribe(v -> System.out.println("Late:  " + v));
        s.onNext(3);
    }
}