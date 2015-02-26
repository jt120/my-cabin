package com.jt.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author ze.liu
 * @since 2014/5/26
 */
public class Worker implements Runnable {

    private CountDownLatch start;
    private CountDownLatch finish;

    public Worker(CountDownLatch start, CountDownLatch finish) {
        this.start = start;
        this.finish = finish;
        System.out.println("a worker is created");
    }

    @Override
    public void run() {
        try {
            start.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " go");
        System.out.println(Thread.currentThread().getName() + " finish");
        finish.countDown();
    }
}
