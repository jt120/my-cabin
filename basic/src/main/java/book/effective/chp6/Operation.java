package book.effective.chp6;

/**
 * Created by ze.liu on 2014/7/10.
 */
public enum  Operation {

    ADD,MINUS,TIMES,DIVIDE;

    double applay(double x, double y) {
        switch (this) {
            case ADD: return x+y;
            case MINUS:return x-y;
            case TIMES:return x*y;
            case DIVIDE:return x/y;
        }
        throw new AssertionError("unknown");
    }

    public static void main(String[] args) {
        double d = ADD.applay(1,2);
        System.out.println(d);
    }
}
