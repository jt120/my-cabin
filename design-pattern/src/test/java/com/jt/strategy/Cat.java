package com.jt.strategy;

import java.lang.*;

/**
 * @author ze.liu
 * @since 2014/5/16
 */
public class Cat implements java.lang.Comparable<Cat> {

    private Comparator comparator;

    public Comparator getComparator() {
        return comparator;
    }

    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    private int weight;
    private int height;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int compareTo(Cat o) {
        return comparator.compare(this, o);
    }
}
