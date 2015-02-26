package com.jt.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ze.liu
 * @since 2014/6/20
 */
public class Provider {

    List<Consumer> consumerList = new ArrayList<Consumer>();

    private Map<String, String> data = new HashMap<String, String>();

    public void addConsumer(Consumer consumer) {
        consumerList.add(consumer);
    }

    public void removeConsumer(Consumer consumer) {
        consumerList.remove(consumer);
    }

    public String getData(String key) {
        return data.get(key);
    }

    public void setData(String key, String value) {
        data.put(key, value);
    }

    public void notifyConsumer() {
        for(Consumer consumer:consumerList) {
            consumer.reload();
        }
    }

    public static void main(String[] args) {
        Provider provider = new Provider();

        Consumer c1 = new Consumer("c1", provider);
        Consumer c2 = new Consumer("c2", provider);

        c1.setData("hello");
        c2.setData("ok");

        for (int i = 0; i < 100; i++) {
            System.out.println("i: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(c1).start();
            new Thread(c2).start();
            if(i == 5) {
                c1.setData("jjj");
                c2.setData("bbb");
            }
            if(i == 10) {
                provider.notifyConsumer();
            }
            if(i == 20) {
                c1.shutdown();
            }
            if(i == 30) {
                c2.shutdown();
            }
        }
    }
}
