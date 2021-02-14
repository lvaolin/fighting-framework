package com.dhy.rxjava.rxjava2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 链式编程
 */
public class HelloRxFluent {

    public static void main(String[] args) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe( ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("hello ");
                emitter.onNext("rx");
                emitter.onNext("java2");
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext( String s) {
                        System.out.println(Thread.currentThread().getName()+"=="+s);
                    }

                    @Override
                    public void onError( Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        for (;;){}
    }
}
