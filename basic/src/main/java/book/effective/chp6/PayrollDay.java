package book.effective.chp6;

/**
 * Created by ze.liu on 2014/7/10.
 */
public enum  PayrollDay {

    OK;

    private enum PayType {
        WEEKDAY {
            @Override
            double overtimePay(double hours, double payRate) {
                return hours;
            }
        },WEEKEND {
            @Override
            double overtimePay(double hours, double payRate) {
                return 0;
            }
        };

        private static final int HOURS = 8;

        abstract double overtimePay(double hours, double payRate);

        double pay(double hoursWorked, double payRate) {
            double basePay = hoursWorked * payRate;
            return basePay + overtimePay(hoursWorked, payRate);
        }
    }
}
