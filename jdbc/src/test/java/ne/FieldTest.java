package ne;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author ze.liu
 * @since 2014/5/23
 */
public class FieldTest {

    @Test
    public void test01() {
        String s = "model.User";
        try {
            Class clz = Class.forName(s);
            //获取public
            Field[] fields = clz.getFields();
            for (Field field : fields) {
                System.out.println("name: " + field.getName() + "type: " + field.getType());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        String s = "model.User";
        try {
            Class clz = Class.forName(s);
            //获取所有
            Field[] fields = clz.getDeclaredFields();
            for (Field field : fields) {
                System.out.println("name: " + field.getName() + " type: " + field.getType());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void test03() {
        String s = "model.User";
        try {
            Class clz = Class.forName(s);
            Field[] fields = clz.getFields();
            for (Field field : fields) {
                //class java.lang.String
                Class c = field.getType();
                System.out.println(c);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test04() {
        String s = "model.User";
        try {
            Class clz = Class.forName(s);
            Field[] fields = clz.getFields();
            for (Field field : fields) {
                Class c = field.getType();
                if("java.lang.String".equals(c.getName())) {
                    System.out.println(c);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
