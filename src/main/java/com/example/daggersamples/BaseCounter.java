package com.example.daggersamples;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseCounter implements Counter {
    private Logger logger;

    public BaseCounter() {
        logger = LogManager.getLogger(this.getClass().getSimpleName());
    }

    @Override
    public void print() {
        log("printing...");
    }

    void log(String s) {
        logger.trace(s);
    }

    void log(Integer i) {
        logger.trace(i);
    }
}
