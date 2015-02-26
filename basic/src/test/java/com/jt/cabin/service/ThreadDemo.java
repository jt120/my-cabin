package com.jt.cabin.service;

/**
 * @author ze.liu
 * @since 2014/5/14
 */
public class ThreadDemo {

    public static void say(String name) {
        System.out.println("run: " + name);
    }

    private static class MyThread implements Runnable {

        private String name;

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            say(name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new MyThread(i + "")).start();
        }
    }
}
