package com.example.daggersamples;

import javax.inject.Inject;

final class LazyCounters {
    @Inject
    LazyCounter counter1;
    @Inject
    LazyCounter counter2;

    void print() {
        counter1.print();
        counter2.print();
    }
}