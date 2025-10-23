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

    //TODO
    // Should handle decimal division
    // Should throw exception when dividing by zero
    @Test
    void shouldDivideTwoNumbers() {
        Calculator calc = new Calculator();
        double result = calc.divide(10, 5);
        assertEquals(2, result);
    }

    @Test
    void ShouldHandleDecimalDivision() {
        Calculator calc = new Calculator();
        double result = calc.divide(10.0, 2.0);
        assertEquals(5.0, result);
    }
    //TODO
    // Should calculate power of a number
    // Should handle power of a zero
    // Should calculate square root
    // Should throw exception for negative square root

}
