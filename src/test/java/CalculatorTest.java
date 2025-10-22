import ax.ha.it.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    void AddNumbers() {
        Calculator calc = new Calculator();
        int result = calc.add(2, 3);
        assertEquals(5, result);
    }

    @Test
    void AddZero() {
        Calculator calc = new Calculator();
        int result = calc.add(0, 2);
        assertEquals(2, result);
    }

    @Test
    void AddNegativeNumbers() {
        Calculator calc = new Calculator();
        int result = calc.add(-2, -3);
        assertEquals(-5, result);
    }

    @Test
    void SubtractNumbers() {
        Calculator calc = new Calculator();
        int result = calc.subtract(3, 1);
        assertEquals(2, result);
    }

    @Test
    void NegativeAnswer() {
        Calculator calc = new Calculator();
        int result = calc.subtract(2, 3);
        assertEquals(-1, result);
    }

    //TODO
    // Should multiply two numbers
    // Should return zero when multiplying by zero
    @Test
    void MultiplyNumbers() {
        Calculator calc = new Calculator();
        int result = calc.multiply(2, 3);
        assertEquals(2, result);
    }
}
