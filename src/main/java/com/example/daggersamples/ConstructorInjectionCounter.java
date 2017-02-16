package com.example.daggersamples;

import javax.inject.Inject;

public class ConstructorInjectionCounter extends BaseCounter {
    private Integer value;

    @Inject
    public ConstructorInjectionCounter(Integer value) {
        this.value = value;
    }

    @Override
    public void print() {
        super.print();
        log(value);
        log(value);
        log(value);
    }
}
