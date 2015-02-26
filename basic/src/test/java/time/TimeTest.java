package time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ze.liu
 * @since 2014/5/23
 */
public class TimeTest {

    private String yyyy = "yyyy-MM-dd HH:mm";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");

    @Test
    public void test01() {
        String s = "01:30";
        String n1 = s.substring(0,2);
        String n2 = s.substring(s.indexOf(":")>-1?3:2);

        System.out.println(n1);
        System.out.println(n2);
    }

    @Test
    public void test02() {
        String s = "0130";
        String n1 = s.substring(0, 2);
        String n2 = s.substring(s.indexOf(":") > -1 ? 3 : 2);

        System.out.println(n1);
        System.out.println(n2);
    }

    @Test
    public void test03() {
        long a1 = 635523846964140000L;
        long a2 = 635523846920940000L;

        DateTime dateTime = new DateTime(a1);
        System.out.println(dateTime.toString());
        DateTime dateTime2 = new DateTime(a2);
        System.out.println(dateTime2.toString("yyyy-MM-dd"));
    }

    @Test
    public void test04() {
        DateTime dateTime = new DateTime();
        long a1 = dateTime.getMillis();
        System.out.println(dateTime.toString("yyyy-MM-dd"));
    }

    @Test
    public void test05() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        long a1 = date.getTime();

        DateTime dateTime = new DateTime(a1);
        System.out.println(dateTime.toString("yyyy-MM-dd"));
    }

    @Test
    public void test06() throws Exception {
        String s = "2014-05-28";

        Date date = simpleDateFormat.parse(s);
        long a1 = date.getTime();
        System.out.println(a1);
        DateTime dateTime = new DateTime(a1);
        System.out.println(dateTime.toString("yyyy-MM-dd"));
    }

    @Test
    public void test07() {
        long a1 = 1401206400000L;
        DateTime dateTime = new DateTime(a1);
        System.out.println(dateTime.toString(yyyy));
    }

    @Test
    public void test08() throws ParseException {
        long a1 = 635523846883200000L;
        String s = "20140919";
        Date d = yyyyMMdd.parse(s);
        System.out.println(a1==d.getTime());
    }


    @Test
    public void test09() {
        DateTime dateTime = new DateTime();
        long a1 = dateTime.getMillis();
        System.out.println(a1);
    }

    @Test
    public void test10() {
        String s = "2014-09-19";
        long a1 = DateTime.parse(s).getMillis();
        System.out.println(a1);
    }

    @Test
    public void test11() {
         long a1 = 1413040200000L;
        DateTime dateTime = new DateTime(a1);
        System.out.println(dateTime.toString(yyyy));
    }


    @Test
    public void test12() {
        DateTime d1 = new DateTime();
        DateTime d2 = d1.minusYears(1);
        DateTime d3 = d1.minusYears(1);
        System.out.println(d2.isAfter(d3));
    }

    @Test
    public void test13() {
        //2014-10-12
        //2014-10-11
        long a1 = 1413040200000L;
        long a2 = 1413050100000L;

        DateTime startLimitDate = new DateTime(a1);
        DateTime endLimitDate = new DateTime(a1);
        DateTime passBirth = new DateTime(a2);
        String pattern = "yyyyMMdd";
        int startLimit = Integer.parseInt(startLimitDate.toString(pattern));
        System.out.println(startLimitDate.toString(pattern));
        int endLimit = Integer.parseInt(endLimitDate.toString(pattern));
        System.out.println(endLimitDate.toString(pattern));
        int birthInt = Integer.parseInt(passBirth.toString(pattern));
        System.out.println(passBirth.toString(pattern));
        boolean b =  birthInt >= startLimit && birthInt <= endLimit;
        System.out.println(b);
    }


    @Test
    public void test14() {
        //2014-10-12
        //2014-10-11
        long a1 = 1413040200000L;

        DateTime passBirth = new DateTime(a1);
        DateTime endLimitDate = passBirth.plusDays(1);
        DateTime startLimitDate = passBirth.minusDays(1);

        boolean b = passBirth.isAfter(startLimitDate)&&passBirth.isBefore(endLimitDate);
        System.out.println(b);
    }

    @Test
    public void test15() {
        String str = "1987-07-01";
        DateTimeZone dateTimeZone = DateTimeZone.getDefault();
        System.out.println("timezone " + dateTimeZone.toString());
        DateTime dateTime = DateTime.parse(str);
        System.out.println(dateTime.toLocalDate());
    }

    @Test
    public void test16() {
        MutableDateTime mutableDateTime = new MutableDateTime();
        mutableDateTime.setHourOfDay(2);
        System.out.println(mutableDateTime.toString());
    }

    @Test
    public void test17() {
        MutableDateTime now = new MutableDateTime();
        now.setHourOfDay(2);
        now.setMinuteOfHour(0);
        now.setSecondOfMinute(0);
        now.setMillisOfSecond(0);
        DateTime myDate = now.toDateTime();
    }

    @Test
    public void test18() {
        String s = "1987-04-12";
        DateTimeZone dateTimeZone = DateTimeZone.UTC;

        DateTime dateTime = DateTime.parse("1987-04-12");
        MutableDateTime now = new MutableDateTime();
        now.setYear(1987);
        now.setMonthOfYear(04);
        now.setDayOfMonth(12);

        DateTime myDate = now.toDateTime();
        System.out.println(myDate.toLocalDate());
        System.out.println(dateTime.toLocalDate());
    }

    @Test
    public void test19() {
        String s = "2014-04-13T02:00:00.000-07:00";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat1.parse(s);
            DateTime dateTime = new DateTime(date);
            System.out.println(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test20() {
        long l = 545155200000L;
        DateTime dateTime = new DateTime(l);
        System.out.println(dateTime);
        dateTime.toString();
    }
}
