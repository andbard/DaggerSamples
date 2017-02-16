package com.example.daggersamples;

public class TimeConsumingInstantiationCounter extends BaseCounter {
    private Integer value;

    public TimeConsumingInstantiationCounter(Integer value) {
        log("starting time consuming initialization...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.value = value;
        log("...time consuming initialization performed");
    }

    @Override
    public void print() {
        super.print();
        log(value);
        log(value);
        log(value);
    }
}
