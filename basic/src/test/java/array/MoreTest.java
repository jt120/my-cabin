package array;

import org.junit.Test;

/**
 * Created by ze.liu on 2014/8/20.
 */
public class MoreTest {

    @Test
    public void test01() {
        String s = "100.0";
        int i = (int) Double.parseDouble(s);
        System.out.println(i);
    }

    @Test
    public void test02() {
        System.out.println("start ");

        try {
            System.out.println("in");
            me1();
            System.out.println("cont");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void me1() {
        throw new RuntimeException("hello");
    }
}
