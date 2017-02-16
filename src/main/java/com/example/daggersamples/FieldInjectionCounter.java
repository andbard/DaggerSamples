package com.example.daggersamples;

import javax.inject.Inject;

final class FieldInjectionCounter extends BaseCounter {
    @Inject
    Integer value;

    @Override
    public void print() {
        super.print();
        log(value);
        log(value);
        log(value);
    }
}