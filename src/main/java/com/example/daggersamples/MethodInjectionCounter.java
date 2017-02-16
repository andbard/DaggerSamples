package com.example.daggersamples;

import javax.inject.Inject;

final public class MethodInjectionCounter extends BaseCounter {
    private Integer value;

    @Inject
    public void setValue(Integer value) {
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
