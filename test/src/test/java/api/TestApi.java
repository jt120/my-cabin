package api;

import org.junit.Test;
import qunar.api.pojo.ShortDate;

import java.util.Date;

/**
 * Created by ze.liu on 2015/1/15.
 */
public class TestApi {

    @Test
    public void test01() {
        final ShortDate shortDate = new ShortDate(20140501);
        System.out.println(shortDate);
        System.out.println(ShortDate.today());
        System.out.println(ShortDate.valueOf("2015-05-01"));
        System.out.println(ShortDate.valueOf(new Date()));
    }

    @Test
    public void test02() {
        Date date = new Date();
        final int year = date.getYear();
        final int day = date.getDay();
        final int month = date.getMonth();
        System.out.println(year+":"+month+":"+day);
    }

    @Test
    public void test03() throws Exception {
        String s = null;
        if (true) {
            System.out.println("in");
        }
        System.out.println(s.substring(123));
    }
}
