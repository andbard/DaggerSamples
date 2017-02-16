package com.example.daggersamples;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executor;

public class TestTask implements Runnable {
    private static final Logger logger = LogManager.getLogger(TestTask.class.getSimpleName());
    private Executor executor;
    private Object lock;

    public TestTask(Executor executor, Object lock) {
        this.executor = executor;
        this.lock = lock;
    }

    @Override
    public void run() {
        // create the injector instance
        CounterComponent component = DaggerCounterComponent.create();

        ConstructorInjectionCounter ciCounter;
        MethodInjectionCounter miCounter = new MethodInjectionCounter();
        FieldInjectionCounter fiCounter = new FieldInjectionCounter();

        ProviderCounter providerCounter = new ProviderCounter();
        LazyCounter lazyCounter = new LazyCounter();

        LazyCounters lazyCounters = new LazyCounters();

        final TimeConsumingInstantiationCounter[] tciCounter = new TimeConsumingInstantiationCounter[1];

        logger.trace("ready to request for ConstructorInjectionCounter instance");
        ciCounter = component.getDirectCounterConstructorInjection();
        logger.trace("ready to call print() on ConstructorInjectionCounter instance");
        ciCounter.print();
        System.out.println("");

        logger.trace("ready to inject MethodInjectionCounter instance");
        component.inject(miCounter);
        logger.trace("ready to call print() on MethodInjectionCounter instance");
        miCounter.print();
        System.out.println("");

        logger.trace("ready to inject FieldInjectionCounter instance");
        component.inject(fiCounter);
        logger.trace("ready to call print() on FieldInjectionCounter instance");
        fiCounter.print();
        System.out.println("");

        logger.trace("ready to inject ProviderCounter instance");
        component.inject(providerCounter);
        logger.trace("ready to call print() on ProviderCounter instance");
        providerCounter.print();
        System.out.println("");

        logger.trace("ready to inject LazyCounter instance");
        component.inject(lazyCounter);
        logger.trace("ready to call print() on LazyCounter instance");
        lazyCounter.print();
        System.out.println("");

        logger.trace("ready to inject LazyCounters instance");
        component.inject(lazyCounters);
        logger.trace("ready to call print() on LazyCounters instance");
        lazyCounters.print();
        System.out.println("");

        DisposableObserver<TimeConsumingInstantiationCounter> observer = new DisposableObserver<TimeConsumingInstantiationCounter>() {
            @Override
            public void onNext(TimeConsumingInstantiationCounter counter) {
                tciCounter[0] = counter;
            }
            @Override
            public void onError(Throwable throwable) {}
            @Override
            public void onComplete() {
                tciCounter[0].print();
                synchronized (lock) {
                    lock.notify();
                }
            }
        };
        component.getTimeConsumingInstantiationCounterObservable()
                .observeOn(Schedulers.from(executor))
                .subscribeWith(observer);
    }
}
