package com.jt.factory;

/**
 * @author ze.liu
 * @since 2014/5/16
 */
public class AnimalFactory implements AbstractFactory <Animal> {

    @Override
    public Animal create() {
        return new Monkey();
    }
}
