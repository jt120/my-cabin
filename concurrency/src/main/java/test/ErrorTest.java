package test;

/**
 * @author ze.liu
 * @since 2014/6/9
 */
public class ErrorTest {

    int num = 0;

    void add() {
        num++;
    }

    void minus() {
        num--;
    }

    int getNum() {
        return num;
    }

    public static void main(String[] args) {
        final ErrorTest errorTest = new ErrorTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1; i++) {
                    errorTest.add();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1; i++) {
                    errorTest.minus();
                }
            }
        }).start();

        for (int i = 0; i < 1; i++) {
            System.out.println(errorTest.getNum());
        }
    }
}
