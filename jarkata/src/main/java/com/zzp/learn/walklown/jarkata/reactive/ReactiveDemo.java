package com.zzp.learn.walklown.jarkata.reactive;

import java.util.concurrent.Flow;

public class ReactiveDemo {

    public static void main(String[] args) {
        OneShotPublisher publisher = new OneShotPublisher();
        publisher.subscribe(new Flow.Subscriber<Boolean>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                subscription.request(3);
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(Boolean item) {
                System.out.println(item);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

    }


}
