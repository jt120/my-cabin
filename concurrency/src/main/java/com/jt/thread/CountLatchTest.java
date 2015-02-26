package com.jt.thread;

import sun.management.Sensor;

import java.util.concurrent.CountDownLatch;

/**
 * @author ze.liu
 * @since 2014/5/26
 */
public class CountLatchTest {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        final CountDownLatch second = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is ready");
                try {
                    second.await();
                    System.out.println(Thread.currentThread().getName() + " run");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is ready");
                try {
                    second.await();
                    System.out.println(Thread.currentThread().getName() + " run");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        second.countDown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " finish");
    }
}
