package time;

import org.junit.Assert;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ze.liu
 * @since 2014/6/11
 */
public class TimeThread2 {

    public static void main(String[] args) {



        ExecutorService executorService = Executors.newFixedThreadPool(50);

        for (int i = 0; i < 10000; i++) {

            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    String s = "2014-06-11";
                    Date d2 = new Date();
                    Date d = null;
                    try {
                        d = GetDate.getFormat().parse(s);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " date: " + d);
                    Assert.assertEquals(d, d2);
                }
            });
        }

        executorService.shutdown();
    }
}

