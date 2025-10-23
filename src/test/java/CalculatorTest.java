import ax.ha.it.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    void AddNumbers() {
        Calculator calc = new Calculator();
        double result = calc.add(2, 3);
        assertEquals(5, result);
    }

    @Test
    void AddZero() {
        Calculator calc = new Calculator();
        double result = calc.add(0, 2);
        assertEquals(2, result);
    }

    @Test
    void AddNegativeNumbers() {
        Calculator calc = new Calculator();
        double result = calc.add(-2, -3);
        assertEquals(-5, result);
    }

    @Test
    void SubtractNumbers() {
        Calculator calc = new Calculator();
        double result = calc.subtract(3, 1);
        assertEquals(2, result);
    }

    @Test
    void NegativeAnswer() {
        Calculator calc = new Calculator();
        double result = calc.subtract(2, 3);
        assertEquals(-1, result);
    }

    @Test
    void MultiplyNumbers() {
        Calculator calc = new Calculator();
        double result = calc.multiply(2, 3);
        assertEquals(6, result);
    }

    @Test
    void MultiplyZero() {
        Calculator calc = new Calculator();
        double result = calc.multiply(0, 2);
        assertEquals(0, result);
    }

    @Test
    void shouldDivideTwoNumbers() {
        Calculator calc = new Calculator();
        double result = calc.divide(10, 5);
        assertEquals(2, result);
    }

    @Test
    void shouldHandleDecimalDivision() {
        Calculator calc = new Calculator();
        double result = calc.divide(10.0, 2.0);
        assertEquals(5.0, result);
    }

    @Test
    void shouldThrowExceptionWhenDividingByZero() {
        Calculator calc = new Calculator();
        double result = calc.divide(10.0, 0.0);
        assertEquals(0.0, result);
    }

    @Test
    void shouldCalculatePowerofaNumber() {
        Calculator calc = new Calculator();
        double result = calc.power(2, 2);
        assertEquals(4, result);
    }

    @Test
    void shouldHandlePowerofaZero() {
        Calculator calc = new Calculator();
        double result = calc.power(2, 0);
        assertEquals(1, result);
    }

    @Test
    void shouldCalculateSquareRoot() {
        Calculator calc = new Calculator();
        double result = calc.squareRoot(10);
        assertEquals(3.1622776601683795, result);
    }

    @Test
    void shouldThrowExceptionForNegativeSquareRoot() {
        Calculator calc = new Calculator();
        double result = calc.squareRoot(-2);
        assertEquals(-1, result);
    }

    //TODO
    // Should handle maximum integer values in addition
    // Should handle negative exponents
    // Should handle zero base with positive exponent
    // Should handle zero squared

    @Test
    void shouldHandleMaximumIntegerValuesInAddition() {
        Calculator calc = new Calculator();
        double result = calc.add(2, 3);
        assertEquals(6, result);
    }

}
