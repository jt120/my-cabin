package com.jt;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by ze.liu on 2014/7/30.
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String,String>();
        for (int i = 0; i < 10000; i++) {
            map.put(i+"",i+"");
        }
    }

    @Test
    public void test11() {
        String s = "YIN/YUE TONG";
        Pattern.compile("[A-Za-z]+/([A-Za-z]+\\s?)+");
        System.out.println(s.matches("[A-Za-z]+/([A-Za-z]+\\s?)+"));
    }

    @Test
    public void test() {
        String s = "hello=#123";
        try {
            String s2 = URLEncoder.encode(s,"utf-8");
            System.out.println(s2);

            String[] s3 = s2.split("=");
            for(String s4:s3) {
                System.out.println(s4);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
