package com.example.daggersamples;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Module
final class CounterModule {
    static final Logger logger = LogManager.getLogger(CounterModule.class.getSimpleName());
    int next = 100;

    @Provides
    Integer provideInteger() {
        logger.trace("providing Integer...");
        return next++;
    }

    @Provides
    LazyCounter provideLazyCounter(Lazy<Integer> lazy) {
        logger.trace("providing LazyCounter...");
        return new LazyCounter(lazy);
    }

    @Provides
    Observable<TimeConsumingInstantiationCounter> provideTimeConsumingInstantiationCounterObservable(final Integer value) {
        logger.trace("providing Observable<TimeConsumingInstantiationCounter>...");
        // this Module class is in charge to instantiate the required dependencies
        // let define here on which thread will perform the instantiation through subscribeOn()
        return Observable.create(new ObservableOnSubscribe<TimeConsumingInstantiationCounter>() {
            @Override
            public void subscribe(ObservableEmitter<TimeConsumingInstantiationCounter> observableEmitter) {
                TimeConsumingInstantiationCounter instance = new TimeConsumingInstantiationCounter(value);
                observableEmitter.onNext(instance);
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.computation());
    }
}