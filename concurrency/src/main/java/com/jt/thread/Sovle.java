package com.jt.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ze.liu
 * @since 2014/5/26
 */
public class Sovle {

    private final CyclicBarrier cyclicBarrier;

    private int[] nums = {1, 2, 3};

    public void setNums(int i, int num) {
        nums[i] = num;
    }

    class Worker implements Runnable {

        int i;
        int num;

        public Worker(int i, int num) {
            this.i = i;
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println("num: " + num);
            num = num * 2;
            nums[i] = num;
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

    public Sovle(int [] data) {
        nums = data;
        cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                merge();
            }
        });
        for (int i = 0; i < 3; i++) {
            final int num = i;
            new Thread(new Worker(num, nums[num])).start();
        }

    }

    public void merge() {
        for (int i = 0; i < 3; i++) {
            System.out.println(nums[i]);
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3};
        final Sovle sovle = new Sovle(a);

    }
}
