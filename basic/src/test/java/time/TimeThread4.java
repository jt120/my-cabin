package time;

import org.junit.Assert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ze.liu
 * @since 2014/6/11
 */
public class TimeThread4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {



        ExecutorService executorService = Executors.newFixedThreadPool(50);

        Callable<Date> callable = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
//把getFormat改成synron的也没用
//                return GetDate.getFormat().parse("1989-1-1");
                return GetDate.parse("1998-1-1");
            }
        };

        List<Future<Date>> list = new ArrayList<Future<Date>>();

        for (int i = 0; i < 10; i++) {
            Future<Date> dateFuture =executorService.submit(callable);
            list.add(dateFuture);
            //如果这样取，不会有问题
//            dateFuture.get();
        }
//这样会有问题
        for (Future<Date> f:list) {
            System.out.println(f.get());
        }

        executorService.shutdown();
    }
}

