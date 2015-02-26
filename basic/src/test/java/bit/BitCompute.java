package bit;

import org.joda.time.DateTime;
import org.junit.Test;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.Date;

/**
 * @author ze.liu
 * @since 2014/6/4
 */
public class BitCompute {

    @Test
    public void test01() {
        int a = 100;
        String hex = Integer.toBinaryString(a);
        System.out.println(hex);

        int b = ~a;
        String hexB = Integer.toBinaryString(b);
        System.out.println(hexB);
    }




    @Test
    public void test02() {
        int a = 1;
        String hex = Integer.toHexString(a);
        System.out.println(hex);
    }

    @Test
    public void test03() {
        int a = 100;
        String hex = Integer.toBinaryString(a);
        System.out.println(hex);

        int b = ~a;
        String hexB = Integer.toBinaryString(b);
        System.out.println(hexB);
    }

    @Test
    public void test04() {
        int a = 3;
        int b = 6;
        System.out.println(a & b);
        System.out.println(a | b);
        System.out.println(a ^ b);
    }

    @Test
    public void test05() {
        int a = 2;
        System.out.println(a>>1);
    }

    @Test
    public void test06() {
        int a = -1;
        int b = -2;
        System.out.println(a & b);
        System.out.println(a | b);
        System.out.println(a ^ b);
    }

    @Test
    public void test07() {
       Date date = new Date();
        DateTime dateTime = new DateTime(date);
        System.out.println(dateTime.toLocalDate());

    }


}
