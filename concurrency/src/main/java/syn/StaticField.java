package syn;

import cn.itcast.heima2.User;

import java.lang.reflect.Field;

/**
 * @author ze.liu
 * @since 2014/5/20
 */
public class StaticField {

    public static User user = new User("aaa", 22);

    public User user2 = new User("aaa",22);

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        StaticField.user = user;
    }
    int a;
    public void cal() {
        System.out.println(a);
    }

    public static void main(String[] args) {
        StaticField staticField = new StaticField();
        StaticField staticField1 = new StaticField();
        System.out.println(StaticField.user == staticField.user2);
        System.out.println(staticField.getUser() == StaticField.user);
        System.out.println(staticField.user2 == staticField.user2);
        staticField.cal();

        staticField.user = new User("bbb",55);
        System.out.println(staticField.user == staticField1.user);

//        staticField.user2 = new User("ccc",33);
        System.out.println(staticField.user2 + ":" + staticField1.user2);
        System.out.println(staticField.user2 == staticField1.user2);
        try {
            Class claz = Class.forName("syn.StaticField");
            Field[] fields = claz.getFields();
            System.out.println(fields.length);
            for(Field field:fields) {
                System.out.println(field);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
