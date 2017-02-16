package com.example.daggersamples;

import dagger.Component;
import io.reactivex.Observable;

@Component(modules = {
        CounterModule.class
})
public interface CounterComponent {
    ConstructorInjectionCounter getDirectCounterConstructorInjection();
    void inject(FieldInjectionCounter counter);
    void inject(MethodInjectionCounter counter);

    void inject(ProviderCounter counter);
    void inject(LazyCounter counter);
    void inject(LazyCounters counters);

    Observable<TimeConsumingInstantiationCounter> getTimeConsumingInstantiationCounterObservable();
}
