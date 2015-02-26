package thread;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;

public class ThreadPoolTest {

    @Before
    public void setUp() throws Exception {

        if (true) {

            System.out.printf("hello   %n", 1);
        }
    }

    @After
    public void tearDown() throws Exception {

        if (true) {
            System.out.println("hello");

        }

    }

    @Test
    public void test01() {

        boolean a = false;

        boolean b = true;

        boolean c = a & b;

        System.out.println(c);



    }
}