package com.jt.factory;

import org.junit.Test;

/**
 * @author ze.liu
 * @since 2014/5/16
 */
public class TestFactory {

    @Test
    public void test01() {
        Apple apple = (Apple) SimpleFactory.createFruit("apple");
        Banana b = (Banana) SimpleFactory.createFruit("apple");
    }

    @Test
    public void test02() {
        FactoryMethod factoryMethod = new AppleFactory();
        factoryMethod.create();
    }

    @Test
    public void test03() {
        AbstractFactory<Animal> abstractFactory = new AnimalFactory();
        Animal animal = abstractFactory.create();
        animal.shout();

    }
}
