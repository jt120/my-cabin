package com.jt.boot;

import javax.annotation.PostConstruct;

/**
 * @author ze.liu
 * @since 2014/5/15
 */
public class BootClass {

    public BootClass() {
        System.out.println("boot constructer");
    }

    @PostConstruct
    public void startUp() {
        System.out.println("start up");
    }

    public void say() {
        System.out.println("hello");
    }
}
