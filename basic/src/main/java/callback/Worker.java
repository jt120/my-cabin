package callback;

/**
 * @author ze.liu
 * @since 2014/5/30
 */
public class Worker implements Runnable {

    String name;
    Callback callback;

    public Worker(String name, Callback callback) {
        this.name = name;
        this.callback = callback;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        name = "new " + name;
        callback.ack();

    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                '}';
    }
}
