package com.jt.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ze.liu
 * @since 2014/5/26
 */
public class CyclicBarrierTest {

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("cyclic is run");
            }
        });
        for (int i = 0; i < 3; i++) {
            final int num = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(2000));

                        System.out.println(Thread.currentThread().getName() + " is run, wait num: " + (cyclicBarrier
                                .getNumberWaiting()+1));
                        cyclicBarrier.await();

                        Thread.sleep(new Random().nextInt(2000));

                        System.out.println(Thread.currentThread().getName() + " is run, wait num: " + (cyclicBarrier
                                .getNumberWaiting() + 1));
                        cyclicBarrier.await();

                        Thread.sleep(new Random().nextInt(2000));

                        System.out.println(Thread.currentThread().getName() + " is run, wait num: " + (cyclicBarrier
                                .getNumberWaiting() + 1));
                        cyclicBarrier.await();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            };

            executorService.execute(runnable);

        }

        executorService.shutdown();

    }
}
