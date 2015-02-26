package time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ze.liu
 * @since 2014/6/11
 */
public class ThreadTime3 {

    public static void main(String[] args) {


        final SimpleDateFormat format =
                new SimpleDateFormat("yyyyMMdd");


        Callable<Date> task = new Callable<Date>() {
            public Date call() throws Exception {
                return format.parse("20101022");
            }
        };


        ExecutorService exec = Executors.newFixedThreadPool(5);
        List<Future<Date>> results =
                new ArrayList<Future<Date>>();


        for (int i = 0; i < 10; i++) {
            results.add(exec.submit(task));
        }
        exec.shutdown();


        for (Future<Date> result : results) {
            try {
                System.out.println(result.get());
            } catch (InterruptedException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
