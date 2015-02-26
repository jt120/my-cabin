package time;

import org.joda.time.*;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;

import java.util.Locale;
import java.util.Set;

/**
 * @author ze.liu
 * @since 2014/6/5
 */
public class JodaClass1 {

    public boolean isAfterPayDay(DateTime datetime) {
        if (datetime.getMonthOfYear() == 2) {   // February is month 2!!
            return datetime.getDayOfMonth() > 26;
        }
        return datetime.getDayOfMonth() > 28;
    }

    public Days daysToNewYear(LocalDate fromDate) {
        LocalDate newYear = fromDate.plusYears(1).withDayOfYear(1);
        return Days.daysBetween(fromDate, newYear);
    }

    public boolean isRentalOverdue(DateTime datetimeRented) {
        Period rentalPeriod = new Period().withDays(2).withHours(12);
        return datetimeRented.plus(rentalPeriod).isBeforeNow();
    }

    public String getBirthMonthText(LocalDate dateOfBirth) {
        return dateOfBirth.monthOfYear().getAsText(Locale.ENGLISH);
    }

    @Test
    public void test01() {
        DateTime dateTime = new DateTime();
        DateTime dateTime1 = dateTime.withYear(2011).withMonthOfYear(12);
        dateTime.withMonthOfYear(12);
        dateTime.plusHours(2);
        System.out.println(dateTime1);
    }

    @Test
    public void test02() {
        Chronology chronology = CopticChronology.getInstance();
        System.out.println(chronology.toString());
    }

    @Test
    public void test03() {
        DateTimeZone zone = DateTimeZone.forID("Asia/Tokyo");
        Chronology gregorianJuian = GJChronology.getInstance(zone);
    }

    @Test
    public void test04() {
        DateTime dt = new DateTime(2005, 4, 26, 12, 0, 0, 0);
        DateTime plusPeriod = dt.plus(Period.days(1));
        DateTime plusDuration = dt.plus(new Duration(24L * 60L * 60L * 1000L));
        System.out.println(plusPeriod);
        System.out.println(plusDuration);
    }

    @Test
    public void test05() {
        DateTime dateTime = new DateTime();
        DateTime.Property property = dateTime.dayOfWeek();
        String s = property.getAsText();
        System.out.println(s);
        System.out.println(property.getAsShortText());

        System.out.println(dateTime.getDayOfWeek());

        System.out.println(property.get());
    }

    @Test
    public void test06() {
        Set<String> stringSet = DateTimeZone.getAvailableIDs();
        for(String s:stringSet) {
            System.out.println(s);
        }
    }

    @Test
    public void test07() {
        DateTime dateTime = new DateTime();
        DateTime dateTime1 = dateTime.withZone(DateTimeZone.forID("Europe/London"));
        System.out.println(dateTime1);
        DateTime dateTime2 = dateTime.withZoneRetainFields(DateTimeZone.forID("Europe/London"));
        System.out.println(dateTime2);
    }

    @Test
    public void test08() {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime.getYear());

        DateTime dateTime1 = dateTime.withChronology(BuddhistChronology.getInstance());
        System.out.println(dateTime1.getYear());
    }

    @Test
    public void test09() {
        DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTime();
        String s = "1987-04-12";
        DateTime dateTime = dateTimeFormatter.parseDateTime(s);
        System.out.println(dateTime);
    }

    @Test
    public void test10() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        String s = "1987-04-12";
        LocalDate dateTime = dateTimeFormatter.parseLocalDate(s);
        System.out.println(dateTime.getYear());
    }

    @Test
    public void test11() {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dateTime = DateTime.parse("1992-05-12T00:08:04.377+08:00", ISODateTimeFormat.dateTimeParser());
        System.out.println(dateTime);
    }

    @Test
    public void test12() {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime);
        System.out.println(dateTime.monthOfYear().getAsText());
    }

    @Test
    public void test13() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dateTime = DateTime.parse("1987-04-12", dateTimeFormatter.withOffsetParsed());
        System.out.println(dateTime);
    }

    @Test
    public void test14() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        //使用下面的方法彻底解决
        LocalDateTime localDateTime = dateTimeFormatter.parseLocalDateTime("1987-04-12");
        System.out.println(localDateTime);
        DateTime dateTime = localDateTime.toDateTime();
        System.out.println(dateTime);
    }

    @Test
    public void test15() {
        DateMidnight dateMidnight = new DateMidnight();
        System.out.println(dateMidnight);
    }

}
