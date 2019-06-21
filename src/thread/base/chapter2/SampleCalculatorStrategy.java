package thread.base.chapter2;

public class SampleCalculatorStrategy implements CalculateStrategy {

    private static final double SALARY_RATE = 0.1;

    private static final double BONUS_RATE = 0.15;

    public double calculateTax(double salary, double bonus) {
        double tax = salary * SALARY_RATE
                + bonus * BONUS_RATE;
        return tax;
    }
}
