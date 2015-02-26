package threadInThread;

import com.google.common.base.Preconditions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ze.liu
 * @since 2014/5/15
 */
public class TestThread {

    @Test
    public void test01() throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(5);
        es.execute(new ParentThread());
        int count = 0;
        while(true) {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " is sleep");
            count ++;
            if(count > 100) {
                break;
            }
        }
        es.shutdown();

    }

    @Test
    public void test02() throws InterruptedException {
        ParentThread[] parentThreads = new ParentThread[10];
        for (int i = 0; i < 10; i++) {
            parentThreads[i] = new ParentThread();
        }
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            es.execute(parentThreads[i]);
        }

        int count = 0;
        while (true) {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " is sleep");
            count++;
            if (count > 100) {
                break;
            }
        }
        es.shutdown();

    }

    @Test
    public void test03() {
        List list = new ArrayList();
        Preconditions.checkNotNull(list);
    }
}
