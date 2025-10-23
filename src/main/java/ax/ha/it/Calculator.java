package ax.ha.it;

public class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (a == 0)
            throw new IllegalArgumentException("No dividing with 0 (zero)!!");
        if (b == 0)
            throw new IllegalArgumentException("No dividing with 0 (zero)!!");

        return a / b;
    }

    public double power(int a, int b) {
        return Math.pow(a, b);
    }
}