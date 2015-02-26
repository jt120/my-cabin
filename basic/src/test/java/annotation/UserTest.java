package annotation;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author ze.liu
 * @since 2014/6/24
 */
public class UserTest {

    @Test
    public void test01() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String clzName = "annotation.User";

        Class clz = Class.forName(clzName);

        User u = (User) clz.newInstance();
        u.setName("zhangsan");
        String s = u.show();
        System.out.println(s);
    }

    @Test
    public void test02() throws Exception {
        String clzName = "annotation.User";

        Class clz = Class.forName(clzName);
        User u = (User) clz.newInstance();
        u.setName("lisi");
        Method method = clz.getMethod("show");
        Object s = method.invoke(u);

        System.out.println(s);
    }

    @Test
    public void test03() throws Exception {
        String clzName = "annotation.User";

        Class clz = Class.forName(clzName);
        Method method = clz.getMethod("ok",String.class);
        Object s = method.invoke(clz, "hehe");

        System.out.println(s);
    }
}
