package array.function;

import org.junit.Test;

/**
 * since 2015/2/16.
 */
public class FunctionTest {

    String reverse(String arg) {
        if (arg.length() == 0) {
            return arg;
        } else {
            return reverse(arg.substring(1, arg.length())) + arg.substring(0, 1);
        }
    }

    @Test
    public void test01() throws Exception {
        String s = "hello";
        System.out.println(reverse(s));

    }

    int n = 0;

    int increment() {
        System.out.println("n:" + n);
        return ++n;
    }


    @Test
    public void test02() throws Exception {
        FunctionTest functionTest = new FunctionTest();
        functionTest.increment();
        functionTest.increment();
        functionTest.increment();

        FunctionTest functionTest1 = new FunctionTest();
        functionTest1.increment();
        functionTest1.increment();
    }



}
