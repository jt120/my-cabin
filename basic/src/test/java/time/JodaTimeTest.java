package time;

import org.joda.time.DateTime;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ze.liu
 * @since 2014/5/23
 */
public class JodaTimeTest {

    @Test
    public void test01() {
        DateTime dateTime = new DateTime(2014, 5, 23, 0, 0, 0, 0);
        DateTime d2 = dateTime.minusYears(70).minusDays(1);
        System.out.println(d2.toLocalDate());
    }

    @Test
    public void test02() {
        DateTime dateTime = new DateTime(2014, 5, 23, 0, 0, 0, 0);
        System.out.println(dateTime.toString("yyyy-MM-dd"));
    }

    @Test
    public void test03() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add(i+"");
        }

        System.out.println(list.get(0));
        System.out.println(list.get(list.size()-1));
    }

    @Test
    public void test04() {
        String s = "1987-04-01";
        DateTime dateTime = DateTime.parse(s);
        System.out.println(dateTime.toString("yyyy/MM/dd"));
    }
}
