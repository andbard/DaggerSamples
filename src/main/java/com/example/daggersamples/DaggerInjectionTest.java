package com.example.daggersamples;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DaggerInjectionTest {
    private static final Logger logger = LogManager.getLogger(DaggerInjectionTest.class.getSimpleName());

    public static void main(String[] args) {
        logger.trace("*** test start ***");
        Object lock = new Object();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        TestTask task = new TestTask(executor, lock);
        executor.execute(task);
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        logger.trace("*** test stop ***");
    }
}
