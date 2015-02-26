package za;

/**
 * @author ze.liu
 * @since 2014/6/12
 */
public enum FlightType {

    DIRECT(1),
    RETURN(2);

    int valuel;
    FlightType(int value) {
        this.valuel = value;
    }

    public int getValuel() {
        return valuel;
    }

    public static void main(String[] args) {
        System.out.println(FlightType.DIRECT.getValuel());
        System.out.println(FlightType.DIRECT.name());
        int a = FlightType.DIRECT.ordinal();
        System.out.println(FlightType.RETURN.ordinal());
        System.out.println(a);

        for(FlightType f:FlightType.values()) {
            System.out.println(f);
        }
    }
}
