package start;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * @author ze.liu
 * @since 2014/5/28
 */
public class TestHello {

    @Before
    public void setUp() {
        System.out.println("before");
    }

    @Test
    public void test01() {
        System.out.println("hello");
    }

    @Test
    public void test02() {
        System.out.println("not null");
        String s = null;
        assertNull(s);
    }

    @Test(expected = ArithmeticException.class)
    public void test03() {
        System.out.println(1/0);
    }

    @Test
    public void test04() {
        assertThat("hello.txt",endsWith("txt"));
        assertThat(Arrays.asList("o"), contains("o"));
    }

    @After
    public void tearDown() {
        System.out.println("after");
    }




}
