package com.jt.state;

/**
 * Created by ze.liu on 2014/8/5.
 */
public class Poor implements State {
    @Override
    public void doSomething(StateContext stateContext) {
        System.out.println("I am poor");
        stateContext.changeState(new Rich());
    }
}
