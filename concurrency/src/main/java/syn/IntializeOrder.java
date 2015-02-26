package syn;

/**
 * @author ze.liu
 * @since 2014/5/20
 */

public class IntializeOrder {

    public static void main(String[] args) {
        Subclass sb = new Subclass();
    }
}

class Super {

    static {
        System.out.println(1);
    }

    Super(int i) {
        System.out.println("super" + i);
    }
}

class Subclass extends Super implements Interface {

    static {
        System.out.println(2);
    }

    Super su = new Super(4);

    Subclass() {
        super(3);
        new Super(5);
    }
}

interface Interface {
    static Super su = new Super(0);
}
