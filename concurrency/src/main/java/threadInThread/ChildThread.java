package threadInThread;

import cn.itcast.heima2.ThreadLocalTest;

/**
 * @author ze.liu
 * @since 2014/5/15
 */
public class ChildThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is run in child");
    }

    public void show() {
        System.out.println(Thread.currentThread().getName() + " show");
    }
}
