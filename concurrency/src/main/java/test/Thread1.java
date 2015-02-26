package test;


public class Thread1 implements Runnable {

    /*public void run() {

        synchronized (this) {

            for (int i = 0; i < 5; i++) {

                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);

            }

        }

    }*/

    public static synchronized void show() {
        for (int i = 0; i < 5; i++) {

            System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);

        }
    }

    public void run() {

        show();



    }

    public static void main(String[] args) {

        Thread1 t1 = new Thread1();

        Thread1 t2 = new Thread1();

        Thread ta = new Thread(t1, "A");

        Thread tb = new Thread(t2, "B");

        ta.start();

        tb.start();

    }

}