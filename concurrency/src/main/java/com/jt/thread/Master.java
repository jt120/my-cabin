package com.jt.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author ze.liu
 * @since 2014/5/26
 */
public class Master {

    public static void main(String[] args) {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch finish = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            new Thread(new Worker(start, finish)).start();
        }
        System.out.println("start here");
        start.countDown();
        System.out.println("end start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            finish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " all is finished");

    }
}
