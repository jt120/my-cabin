package com.jt.strategy;

/**
 * @author ze.liu
 * @since 2014/5/16
 */
public class CatHeightComparator implements Comparator <Cat> {
    @Override
    public int compare(Cat t1, Cat t2) {
        return t1.getHeight()-t2.getHeight();
    }
}
