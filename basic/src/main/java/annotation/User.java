package annotation;

/**
 * @author ze.liu
 * @since 2014/6/24
 */
public class User {

    private String name;

    public String show() {
        System.out.println("name:" + this.name);
        return "result:" + this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String ok(String word) {
        return word;
    }
}
