package threadInThread;

/**
 * @author ze.liu
 * @since 2014/5/15
 */
public class ParentThread implements Runnable {

    private ChildThread childThread;

    public ParentThread() {
        childThread = new ChildThread();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " is run in parent");
            childThread.show();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ParentThread parentThread = new ParentThread();

        Thread t = new Thread(parentThread);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
