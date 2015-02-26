package time;

import org.joda.time.*;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.JulianChronology;
import org.junit.Test;

/**
 * @author ze.liu
 * @since 2014/6/5
 */
public class JodaExample2 {

    /**
     * 部分时间Partial，与Instant的区别是，不包含时区
     */
    @Test
    public void test01() {
        //年月日
        LocalDate localDate = new LocalDate();
        System.out.println(localDate);
        //时分秒
        LocalTime localTime = new LocalTime();
        System.out.println(localTime);
        //都有
        LocalDateTime localDateTime = new LocalDateTime();
        System.out.println(localDateTime);
    }

    @Test
    public void test02() {
        //夏令时转换
        LocalDate localDate = new LocalDate(1987, 4, 12);
        DateTime dateTime = localDate.toDateTimeAtStartOfDay();
        System.out.println(dateTime);

        LocalDateTime localDateTime = new LocalDateTime(1987, 4, 12, 0, 0);
        System.out.println(localDateTime);
        //下面转换报异常，因为0点被跳过了
//        localDateTime.toDateTime();
//        java.lang.IllegalArgumentException:Illegal instant due to com.jt.boot.time zone offset transition:1987 - 04 - 11 T16:
//        00:00.000
        //转成LocalDate再用toDateTimeAtStartOfDay
        LocalDate localDate1 = new LocalDate(localDateTime);
        System.out.println(localDate1);
    }

    /**
     * 间隔是两个时间之间的毫秒数
     * 包含时区，但是支持一个时区，一个日历
     * 是半闭半开，start是包含的，end是不包含的，因为end都比start大
     */
    @Test
    public void test03() {
        DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
        DateTime end = new DateTime(2004, 12, 25, 0, 0, 0, 1);
        Interval interval = new Interval(start, end);
        System.out.println(interval.toDurationMillis());

        DateTime startDateTime = interval.getStart();
        DateTime endDateTime = interval.getEnd();

        Duration duration = interval.toDuration();

        Period period = interval.toPeriod();
    }

    /**
     * instant  +  duration  =  instant
     * duration通过间隔获取，只是毫秒数，不含时区
     */
    @Test
    public void test04() {
        DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
        DateTime end = new DateTime(2004, 12, 25, 0, 0, 1, 100);
        Interval interval = new Interval(start, end);
        Duration duration = interval.toDuration();
        System.out.println(duration.getStandardSeconds());

        DateTime startDateTime = new DateTime(2004, 12, 25, 0, 0, 0, 0);
        DateTime endDateTime = new DateTime(2005, 1, 1, 0, 0, 0, 0);

// duration in ms between two instants
        Duration dur = new Duration(start, end);

// calc will be the same as end
        DateTime calc = start.plus(dur);

    }

    /**
     * duration和period的区别是period还可以用年、日表示，但duration只用毫秒数表示
     * <p/>
     * instant  +  period  =  instant
     * 如果你给二月加一个月，那么是三月，给四月加一个月，是五月，但这一个月的毫秒数不同，所以需要period
     */
    @Test
    public void test05() {
        DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
        DateTime end = new DateTime(2006, 1, 1, 0, 0, 0, 0);

// period of 1 year and 7 days
        Period period = new Period(start, end);

// calc will equal end
        DateTime calc = start.plus(period);

// able to calculate whole days between two dates easily
        Days days = Days.daysBetween(start, end);

// able to calculate whole months between two dates easily
        Months months = Months.monthsBetween(start, end);
    }

    /**
     * 年表的概念joda与jdk不同，jdk的实现是通过实现calendar的子类来实现不同的日历，这样不容易扩展
     * joda使用可拔插的方式实现年表，在子类之间添加一个容器，用来装需要的年表
     * 1583之前的日期不使用，因为计算闰年的方式不同
     * The Julian calendar defines a leap year as once every four years.但这是不准确的
     * 有以下几种年表实现
     * <ul>
     * <li>ISO - Based on the ISO8601 standard and suitable for use after about 1600
     * <li>GJ - Historically accurate calendar with Julian followed by Gregorian
     * <li>Gregorian - The Gregorian calendar system used for all com.jt.boot.time (proleptic)
     * <li>Julian - The Julian calendar system used for all com.jt.boot.time (proleptic)
     * <li>Buddhist - The Buddhist calendar system which is an offset in years from GJ
     * <li>Coptic - The Coptic calendar system which defines 30 day months
     * <li>Ethiopic - The Ethiopic calendar system which defines 30 day months
     * </ul>
     */
    @Test
    public void test06() {
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        Chronology coptic = CopticChronology.getInstance(zone);

// current com.jt.boot.time with coptic chronology
        DateTime dt = new DateTime(coptic);

        int year = dt.getYear();   // gets the current coptic year
        int month = dt.getMonthOfYear(); // gets the current coptic month

        // setup date object for the Battle of Hastings in 1066
        Chronology chrono = JulianChronology.getInstance();
        DateTime dt2 = new DateTime(1066, 10, 14, 10, 0, 0, 0, chrono);
    }

    /**
     * 时区的问题
     * 可以下载 tz database，然后重新打包，对joda进行时区的更新（因为时区总是变的，例如中国在86年到91年间实行夏令时，后来又不用了）
     * org.joda.com.jt.boot.time.tz下面是时区库
     */
    @Test
    public void test07() {

    }
}
