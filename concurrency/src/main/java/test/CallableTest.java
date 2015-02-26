package test;

import java.util.concurrent.*;

/**
 * @author ze.liu
 * @since 2014/6/6
 */
public class CallableTest {

    public static void main(String[] args) {
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return "ok";
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future future = executorService.submit(callable);

        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
