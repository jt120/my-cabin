package com.jt.factory;

/**
 * @author ze.liu
 * @since 2014/5/16
 */
public class AppleFactory implements FactoryMethod <Apple> {


    @Override
    public Apple create() {
        return new Apple();
    }
}
