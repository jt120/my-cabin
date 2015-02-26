package callback;

/**
 * @author ze.liu
 * @since 2014/5/30
 */
public class Callback {

    Worker worker;
    Master master;

    public Callback(Master master) {
        this.master = master;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public void ack() {
        master.show(worker);
    }
}
