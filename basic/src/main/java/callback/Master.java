package callback;

/**
 * @author ze.liu
 * @since 2014/5/30
 */
public class Master {




    public void show(Worker worker) {
        System.out.println(worker);
    }

    public static void main(String[] args) {
        Master master = new Master();
        Worker[] workers = new Worker[5];
        for (int i = 0; i < 5; i++) {
            Callback callback = new Callback(master);
            workers[i] = new Worker("worker" + i, callback);
            callback.setWorker(workers[i]);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println("worker start");
            new Thread(workers[i]).start();
        }
    }
}
