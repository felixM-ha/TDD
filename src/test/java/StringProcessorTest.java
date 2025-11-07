import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringProcessorTest {

    private StringProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new StringProcessor();
    }

    @Nested
    @DisplayName("String Reversal Tests")
    class StringReversalTests {

        @Test
        @DisplayName("should reverse a simple string")
        void shouldReverseSimpleString() {
            String result = processor.reverse("hello");
            assertEquals("olleh", result);
        }
    }
}
