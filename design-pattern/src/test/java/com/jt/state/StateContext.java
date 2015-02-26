package com.jt.state;

/**
 * Created by ze.liu on 2014/8/5.
 */
public class StateContext {

    private State currentState;

    public StateContext() {
        currentState = new Poor();
    }

    public void changeState(State state) {
        this.currentState = state;
    }

    public void change() {
        currentState.doSomething(this);
    }

    public static void main(String[] args) {
        StateContext stateContext = new StateContext();
        stateContext.change();
        stateContext.change();
        stateContext.change();
        stateContext.change();
    }
}
