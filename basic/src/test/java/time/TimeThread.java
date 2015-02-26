package time;

import org.junit.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ze.liu
 * @since 2014/6/11
 */
public class TimeThread {

    public static void main(String[] args) {



        ExecutorService executorService = Executors.newFixedThreadPool(50);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Date date = new Date();
                String s = GetDate.getFormat().format(date);
                System.out.println(Thread.currentThread().getName() + " date: " + s);
                Assert.assertEquals(s, "2014-06-11");
            }
        };

        for (int i = 0; i < 10000; i++) {

            executorService.submit(runnable);
        }

        executorService.shutdown();
    }
}

class GetDate {
    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static synchronized DateFormat getFormat() {
        return dateFormat;
    }

    public static synchronized Date parse(String s) throws ParseException {
        return dateFormat.parse(s);
    }
}
