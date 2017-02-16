package com.example.daggersamples.rxtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SimpleRxJavaTest {
    private static final Logger logger = LogManager.getLogger(SimpleRxJavaTest.class);

    public static void main(String[] args) {
        logger.trace("*** SimpleRxJavaTest START ***");
        Object lock = new Object();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        SimpleRxTask task = new SimpleRxTask(executor, lock);
        executor.execute(task);
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        logger.trace("*** SimpleRxJavaTest STOP ***");
    }
}
