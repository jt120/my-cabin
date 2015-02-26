package com.jt.test.service;

/**
 * @author ze.liu
 * @since 2014/5/28
 */
public enum  Fruit {
    APPLE(1,"苹果");

    private int num;
    private String des;
    Fruit(int num, String des) {
        this.num = num;
        this.des = des;
    }
}
