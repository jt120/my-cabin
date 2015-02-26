package com.jt.strategy;

/**
 * @author ze.liu
 * @since 2014/5/16
 */
public class Test {

    public static void main(String[] args) {

        Cat c1 = new Cat(10, 20);
        Cat c2 = new Cat(30, 5);

        Comparator comparator = new CatHeightComparator();

        c1.setComparator(comparator);
        System.out.println(c1.compareTo(c2));

    }
}
