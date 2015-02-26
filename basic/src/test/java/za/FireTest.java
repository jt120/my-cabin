package za;

import org.junit.Test;

/**
 * @author ze.liu
 * @since 2014/6/11
 */
public class FireTest {

    @Test
    public void test01() {
        System.out.println(Thread.currentThread().getName() + " test01");
    }

    @Test
    public void test02() {
        System.out.println(Thread.currentThread().getName() + " test02");
    }

    @Test
    public void test03() {
        System.out.println(Thread.currentThread().getName() + " test03");
    }
}
