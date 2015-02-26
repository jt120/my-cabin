package resourse;

import java.util.ResourceBundle;

/**
 * @author ze.liu
 * @since 2014/6/9
 */
public class ResouseUtil {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("sys");

    public static String get(String name) {
        try {
            return resourceBundle.getString(name);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }

    public static String get(String name, String def) {
        String s = resourceBundle.getString(name);
        if(null==s||"".equals(s.trim())) {
            return def;
        }
        return s;
    }

    public static void main(String[] args) {
        String value = ResouseUtil.get("hello");
        System.out.println(value);

//        String a = ResouseUtil.get("a","world");
//        System.out.println(a);
//
//        String c = ResouseUtil.get("c");
//        System.out.println(c);

    }
}
