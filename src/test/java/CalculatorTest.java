import ax.ha.it.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    //TODO
    // Should add zero to a number
    // Should add negative numbers

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
        assertEquals(2, result);
    }
}
