package com.jt.test.dao;

/**
 * @author ze.liu
 * @since 2014/5/28
 */
public class ThrowSome {

    public void add() throws IllegalArgumentException {
        throw new IllegalArgumentException("insure error:承保范围为1~20周岁。该乘机人年龄已超出承保范围。");
    }

    public static void main(String[] args) {
        ThrowSome throwSome = new ThrowSome();
        throwSome.add();
    }
}
