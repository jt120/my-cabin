package start;

import com.jt.test.dao.ThrowSome;
import org.junit.Test;

/**
 * @author ze.liu
 * @since 2014/5/28
 */
public class TestException {

    @Test(expected = IllegalArgumentException.class)
    public void test01() {
        ThrowSome throwSome = new ThrowSome();
        try {
            throwSome.add();
        } catch (Exception e) {
          String s = e.getMessage();
            String s2 = s.substring(s.indexOf(":")+1);
            System.out.println(s2);
            String[] s3 = s2.split(",");
            int a = Integer.parseInt(s3[0]);
            int b = Integer.parseInt(s3[1]);
            System.out.println("a:" + a);
            System.out.println("b:" + b);
        }

    }
}
