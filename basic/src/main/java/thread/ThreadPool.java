package thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by ze.liu on 2014/8/25.
 */
public class ThreadPool {

    public void start() {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 60L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(50), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "test");
            }
        }
        );
        for (int i = 0; i < 500; i++) {
            final int j = i;
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(j + " active:" + threadPoolExecutor.getActiveCount());
                    System.out.println(j + " size:" + threadPoolExecutor.getPoolSize());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public static void main(String[] args) {
        new ThreadPool().start();
    }
}
