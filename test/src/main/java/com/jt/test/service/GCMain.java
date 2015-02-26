package com.jt.test.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ze.liu on 2014/9/15.
 */
public class GCMain {

    public static void main(String[] args) throws IOException {
        int num1 = 1000000;
        int num2 = 1000000;
        for (int i = 0; i < num1; i++) {
            Map map = new HashMap(num2);
            for (int j = 0; j < num2; j++) {

                map.put(j, j);
            }
            System.out.println("add " + i);

        }

        System.in.read();
    }
}
