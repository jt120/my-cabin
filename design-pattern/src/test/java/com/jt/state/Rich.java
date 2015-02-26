package com.jt.state;

/**
 * Created by ze.liu on 2014/8/5.
 */
public class Rich implements State {
    @Override
    public void doSomething(StateContext stateContext) {
        System.out.println("I am rich");
        stateContext.changeState(new Poor());
    }
}
