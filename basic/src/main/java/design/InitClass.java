package design;

/**
 * Created by ze.liu on 2014/8/19.
 */
public class InitClass {

    private static FieldClass fieldClass = new FieldClass();

    static {
        System.out.println("static");
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
