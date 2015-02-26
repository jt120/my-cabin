/**
 * Created by ze.liu on 2014/8/20.
 */
public class User {

    private String name;

    private int age;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void test() {
        User u = new User("hello",11);
    }

    public void test2(User u) {
        User u1 = new User();
        synchronized (u) {
           int a = 1;
        }
    }
}
