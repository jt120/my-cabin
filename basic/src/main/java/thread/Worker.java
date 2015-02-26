package thread;


/**
 * Created by ze.liu on 2014/9/16.
 */
public class Worker implements Runnable {


    private String name;


    public Worker(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + "===" + Thread.currentThread().getName());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
