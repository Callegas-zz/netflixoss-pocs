package com.callegasdev.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;


public class SimpleCommand extends HystrixObservableCommand<String> {

    private final String products;

    public SimpleCommand(String products) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.products = products;
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext("Avaliable prodcuts : " + products);
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        } );
    }
}