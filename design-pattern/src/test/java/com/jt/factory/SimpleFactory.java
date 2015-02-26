package com.jt.factory;

/**
 * @author ze.liu
 * @since 2014/5/16
 */
public class SimpleFactory {

    public static Object createFruit(String name) {
        if(name.equals("apple")) {
            return new Apple();
        } else if (name.equals("banana")) {
            return new Banana();
        } else {
            return "not supplied";
        }
    }
}
