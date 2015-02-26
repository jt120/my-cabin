package exception;

/**
 * Created by ze.liu on 2014/7/17.
 */
public class ExceptionNow {

    public String hello() {
        throw new RuntimeException("e");
    }

    public static void main(String[] args) {
        ExceptionNow exceptionNow = new ExceptionNow();
        String s = null;
        try {
            s = exceptionNow.hello();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }
}
