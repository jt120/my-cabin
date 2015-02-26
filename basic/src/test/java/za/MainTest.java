package za;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by ze.liu on 2014/7/2.
 */
public class MainTest {

    @Test
    public void test01() {
        String[] s = new String[] {};
        System.out.println(s.length);
        String[] s1 = new String[] {""};
        System.out.println(s1.length);
    }

    @Test
    public void test02() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add(i+"");
        }
        Object[] o = list.toArray();
        boolean b = o.getClass()!= Object[].class;
        System.out.println(b);
        if(b)
            System.out.println("not equal");
    }

    @Test
    public void test03() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("/sys.properties")));
        String name = properties.getProperty("hello");
        System.out.println(name);
    }

    @Test
    public void test04() {
        URL url0 = MainTest.class.getClassLoader().getResource("");
        //下面的会报错
        //URL url1 = MainTest.class.getClassLoader().getResource("/");
        URL url2 = MainTest.class.getResource("");
        URL url3 = MainTest.class.getResource("/");
        System.out.println(url0);//
        System.out.println(url2);
        System.out.println(url3);
    }

    @Test
    public void test05() {
        String s = "\u4e00";
        System.out.println(s);
    }

    @Test
    public void test06() {
    }

}
