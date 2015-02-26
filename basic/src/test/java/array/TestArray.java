package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * since 2015/2/10.
 */
public class TestArray {

    @Test
    public void test01() throws Exception {
        show();
    }

    public static void show() {
        System.out.println(
                Arrays.asList(new String[]{"a", "b"}));

        System.out.println(
                Arrays.asList(new Integer[]{1, 2}));

        System.out.println(
                Arrays.asList(new int[]{1, 2}));

        System.out.println(
                Arrays.asList(new String[]{"a", "b"}, "c"));
    }
}