package zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author ze.liu
 * @since 2014/6/9
 */
public class MyZookeeper implements Watcher {

    private String zkAdd;
    private int timeout;

    private ZooKeeper zooKeeper;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public MyZookeeper(String zkAdd, int timeout) {
        this.zkAdd = zkAdd;
        this.timeout = timeout;
    }

    public void connect() {
        try {
            zooKeeper = new ZooKeeper(zkAdd, timeout, this);
            countDownLatch.await();
            System.out.println("zookeeper connected");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void process(WatchedEvent event) {
       if(event.getState() == Event.KeeperState.SyncConnected) {
           countDownLatch.countDown();
       }
    }

    public void close() {
        try {
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<String> getChild(String path) {
        List<String> list = null;
        try {
            list = zooKeeper.getChildren(path,false);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    public byte[] getData(String path) {
        try {
            return zooKeeper.getData(path, false, null);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void create(String path, String data) {
        try {
            zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyZookeeper myZookeeper = new MyZookeeper("127.0.0.1",2000);
        myZookeeper.connect();
        List<String> list = myZookeeper.getChild("/hello");
        for(String s:list) {
            System.out.println(s);
        }

        byte[] bytes = myZookeeper.getData("/hello/b");
        String s = new String(bytes);
        System.out.println("get data: " + s);

        myZookeeper.create("/hello/d", "myCreate");

        String s2 = new String(myZookeeper.getData("/hello/d"));
        System.out.println("create and get " + s2);

        byte[]  nb = myZookeeper.getData("/hello/noexist");
        System.out.println(nb);

        myZookeeper.close();
    }
}
