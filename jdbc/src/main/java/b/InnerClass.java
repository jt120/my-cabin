package b;

/**
 * @author ze.liu
 * @since 2014/5/22
 */
public class InnerClass {


    public static void main(String[] args) throws Exception {
        String s = "b.InnerClass$Hello";
        Class clz = Class.forName(s);
        clz.newInstance();


        String s1 = "b.InnerClass$OK";
        Class clz1 = Class.forName(s1);
        clz.newInstance();


        String s2 = "b.InnerClass$PrivateHello";
        Class clz2 = Class.forName(s2);
        clz.newInstance();
    }

    /**
     * 因为内部类初始化需要外部类的实例，所以此处必须是static，才能正常初始化
     */
    public static class Hello {


    }

    static class OK {

    }

    private static class PrivateHello {

    }
}
