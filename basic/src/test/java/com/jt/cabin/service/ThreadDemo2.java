package com.jt.cabin.service;

/**
 * @author ze.liu
 * @since 2014/5/14
 */
public class ThreadDemo2 {

    public void stopMin() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void say() {
        System.out.println("start");
        stopMin();
        System.out.println("end");
    }

    public static void main(String[] args) {
        new ThreadDemo2().say();
    }
}
