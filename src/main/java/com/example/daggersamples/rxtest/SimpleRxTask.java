package com.example.daggersamples.rxtest;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executor;

public class SimpleRxTask implements Runnable {
    private static final Logger logger = LogManager.getLogger(SimpleRxJavaTest.class.getSimpleName());
    private Executor executor;
    private Object lock;

    public SimpleRxTask(Executor executor, Object lock) {
        this.executor = executor;
        this.lock = lock;
    }

    @Override
    public void run() {
        performTask();
    }

    private void performTask() {
        logger.trace("instantiate observable");
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                logger.trace("computing...");
                Thread.sleep(1000);
                logger.trace("...computed");
                observableEmitter.onNext(10);
                observableEmitter.onComplete();
            }
        });
        DisposableObserver<Integer> disposableObserver = new DisposableObserver<Integer>() {
            public void onNext(Integer o) {
                logger.trace("onNext() -> " + o);
            }

            public void onError(Throwable throwable) {}

            public void onComplete() {
                logger.trace("onComplete()");
                synchronized (lock) {
                    lock.notify();
                }
            }
        };
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.from(executor))
                .subscribeWith(disposableObserver);
    }
}
