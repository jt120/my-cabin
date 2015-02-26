package book.effective.chp10;

import java.util.concurrent.TimeUnit;

/**
 * Created by ze.liu on 2014/7/10.
 */
public class StopThread {
    private volatile static boolean finish = false;

    public static void main(String[] args) throws InterruptedException {

        Thread bThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(!finish) {
                    i ++;
                    System.out.println(i);
                    //try {
                    //    Thread.sleep(10);
                    //} catch (InterruptedException e) {
                    //    e.printStackTrace();
                    //}
                }
            }
        });
        bThread.start();
        TimeUnit.SECONDS.sleep(1);
        finish = true;

    }
}
