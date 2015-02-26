package com.jt.observer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ze.liu
 * @since 2014/6/20
 */
public class Consumer implements Runnable {

    private Provider provider;

    private String name;

    private boolean alive;

    private Map<String, String> cache = new HashMap<String, String>();

    public Consumer(String name, Provider provider) {
        this.name = name;
        this.provider = provider;
        this.alive = true;
        provider.addConsumer(this);
        provider.setData(name, "");
    }

    public void reload() {
        cache.put(name, provider.getData(name));
    }

    public void show() {
        String value = getData();
        System.out.println("name: " + name + " value: " + value);
    }


    @Override
    public void run() {
        while(alive) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            show();
        }
    }

    public void shutdown() {
        alive = false;
    }

    public void setData(String data) {
        provider.setData(name, data);
    }

    public String getData() {
        String data = cache.get(name);
        if(data == null) {
            data = provider.getData(name);
            cache.put(name, data);
        }
        return data;
    }
}
