package com.jt.pattern.chain;

/**
 * @author ze.liu
 * @since 2014/5/15
 */
public abstract class Handler {

    protected Handler nextHandler;

    protected String name;

    public Handler() {}

    public Handler(String name) {
        this.name = name;
    }

    abstract public void doHandler();

    public Handler getHandler() {
        return nextHandler;
    }

    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }
}
