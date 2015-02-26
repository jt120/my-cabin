package book.effective.chp6;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ze.liu on 2014/7/10.
 */
public enum  OperationGood {

    ADD("+") {
        @Override
        double apply(double x, double y) {
            return x+y;
        }
    },MINUS("-") {
        @Override
        double apply(double x, double y) {
            return x-y;
        }
    },TIMES("*") {
        @Override
        double apply(double x, double y) {
            return x*y;
        }
    },DIVIDE("/") {
        @Override
        double apply(double x, double y) {
            return x/y;
        }
    };
    private final String symbol;
    OperationGood(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    abstract double apply(double x, double y);

    private static final Map<String,OperationGood> map = new HashMap<String,OperationGood>();

    static {
        for(OperationGood operationGood:values()) {
            map.put(operationGood.toString(),operationGood);
        }
    }
    public static OperationGood fromString(String s) {
        return map.get(s);
    }
    public static void main(String[] args) {
        double x = 1;
        double y = 2;
        for(OperationGood op:OperationGood.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}
