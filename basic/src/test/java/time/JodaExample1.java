package time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.Date;
import java.util.Set;

/**
 * 时间Instant
 * Joda中如果表示final的class，就是线程安全的，都会在api中写明
 * @author ze.liu
 * @since 2014/6/5
 */
public class JodaExample1 {

    @Test
    public void test01() {
        DateTime dateTime = new DateTime(2014,6,5,0,0,0);
        System.out.println(dateTime);

        //可以传毫秒数
        long l = 545155200000L;
        DateTime dateTime2 = new DateTime(l);
        System.out.println(dateTime);

        //可以传jdk的date
        Date date = new Date();
        DateTime dateTime1 = new DateTime(date);
        System.out.println(dateTime1);



    }

    /**
     * 日期的操作
     */
    @Test
    public void test02() {
        DateTime dateTime = new DateTime(2014, 6, 5, 0, 0, 0);
        DateTime dateTime1 = dateTime.plusHours(1);
        System.out.println(dateTime1);

        DateTime dateTime2 = dateTime.minusMonths(1);

        System.out.println(dateTime1.monthOfYear().getAsText());
    }

    /**
     * 坑
     * 对夏令时的转换，不能使用DateTime，使用LocalDate
     * 1987-04-12正好是夏令时的开始，这天会把表拨快一小时，然后9月的第二个周日再拨回来，所以0点就没了，如果用DateTime，那么1987-04-12
     * 转成了0点0分，但其实这个时间点是不存在的
     */
    @Test
    public void test03() {
        //夏令时转换
        LocalDate localDate = new LocalDate(2015, 7, 24);
        LocalDate localDate1 = new LocalDate(1935, 1, 1);
        LocalDate localDate2 = localDate.minusYears(80);
        System.out.println(localDate2);
        DateTime dateTime = localDate.toDateTimeAtStartOfDay();
        System.out.println(dateTime);
    }

    /**
     * 打印所有支持的时区
     */
    @Test
    public void test06() {
        Set<String> stringSet = DateTimeZone.getAvailableIDs();
        for (String s : stringSet) {
            System.out.println(s);
        }

        DateTimeZone dateTimeZone = DateTimeZone.forID("Europe/London");
    }
}
