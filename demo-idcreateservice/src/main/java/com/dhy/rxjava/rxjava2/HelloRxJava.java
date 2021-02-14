package com.dhy.rxjava.rxjava2;

import com.mysql.jdbc.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class HelloRxJava {

    public static void main(String[] args) throws InterruptedException {
        //创建一个可观察者（生产者）
        Observable<String> objectObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                //每调用一次 onNext方法就会发出一个事件（消息）
                observableEmitter.onNext("hello ");
                observableEmitter.onNext("rx ");
                observableEmitter.onNext("java ");
            }
        });

        //创建一个消费者
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(Thread.currentThread().getName() + " == " + s);
            }
        };
        //将生产者与消费者关联起来
        objectObservable.subscribe(consumer); //同步消费
        objectObservable.subscribeOn(Schedulers.newThread()).subscribe(consumer); //异步消费


        //将生产者与观察者关联起来
        objectObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe( Disposable disposable) {
                System.out.println("onSubscribe ");
            }

            @Override
            public void onNext( String s) {
                System.out.println(s);
            }

            @Override
            public void onError( Throwable throwable) {

                System.out.println(throwable.getMessage());
            }

            @Override
            public void onComplete() {

                System.out.println("onComplete");
            }
        });

        TimeUnit.SECONDS.sleep(100);

    }
}
