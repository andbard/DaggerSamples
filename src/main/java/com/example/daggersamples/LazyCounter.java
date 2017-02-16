package com.example.daggersamples;

import dagger.Lazy;

import javax.inject.Inject;

final class LazyCounter extends BaseCounter {
    @Inject
    Lazy<Integer> lazy;

    public LazyCounter() {}

    public LazyCounter(Lazy<Integer> lazy) {
        this.lazy = lazy;
    }

    @Override
    public void print() {
        super.print();
        log(lazy.get());
        log(lazy.get());
        log(lazy.get());
    }
}