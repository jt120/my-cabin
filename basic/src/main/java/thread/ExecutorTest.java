package thread;

import java.util.concurrent.*;

/**
 * Created by ze.liu on 2014/9/16.
 */
public class ExecutorTest {

    public static void main(String[] args) {
        ThreadPoolExecutor service = new ThreadPoolExecutor(2, 4, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));

        for (int i = 1; i < 15; i++) {
            Worker worker = new Worker(i+"");
            try {
                /**
                 * 1. 先放入queue，再拿到core pool执行
                 * 2. 如果core pool放慢，那么一直放到queue中，知道queue放满
                 * 3. 如果queue满了，还有任务，那么加大pool到max
                 * 4. 如果pool已经到了max，还有任务，那么reject
                 */
                service.submit(worker);
                System.out.println(i + " pool size " + service.getPoolSize());
                System.out.println(i + " active " + service.getActiveCount());
                System.out.println(i + " task count " + service.getTaskCount());
                System.out.println(i + " queqe size " + service.getQueue().size());
            } catch (Exception e) {
                System.out.println("reject");
            }
        }
UserService.say();
        service.shutdown();
    }
}
