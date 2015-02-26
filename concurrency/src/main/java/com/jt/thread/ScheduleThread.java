package com.jt.thread;


import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author ze.liu
 * @since 2014/5/16
 */
public class ScheduleThread {

    public static void main(String[] args) throws IOException {
        /*Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is run");
            }
        }, 0, 1, TimeUnit.SECONDS);*/


        Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "my thread");
            }
        }).scheduleAtFixedRate(new InnerThread(), 0, 2, TimeUnit.SECONDS);

        System.out.println(Thread.currentThread().getName() + " is run");
//        System.in.read();
    }



    private static class InnerThread implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is run");
        }
    }
}
