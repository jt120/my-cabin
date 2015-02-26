package com.jt.test.service;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;

import java.util.Date;

/**
 * Created by ze.liu on 2014/9/19.
 */
public class TestTime {

    protected boolean checkWorkTime(String tikectTime) {
        String nowTime = DateFormatUtils.format(new Date(), "HHmm");
        int now = NumberUtils.toInt(nowTime);
        tikectTime = StringUtils.replace(tikectTime, ":", "");
        String[] perTime = StringUtils.split(tikectTime, "-");
        if (perTime.length == 2) {
            int beginTime = NumberUtils.toInt(perTime[0]);
            int endTime = NumberUtils.toInt(perTime[1]);
            return (beginTime <= now && endTime >= now);
        }
        return false;
    }


    @Test
    public void test01() {

        String s = "20:05-23:59";

        boolean b = checkWorkTime(s);

        System.out.println(b);
    }

    @Test
    public void test() {

        Date date = new Date(1411034642135L);
        System.out.println(date);
    }

    @Test
    public void test04() throws Exception {
        Integer integer = 12;
        System.out.println(integer.toString());
    }


}
