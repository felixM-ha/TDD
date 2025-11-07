import ax.ha.it.StringProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

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

        @Test
        @DisplayName("should handle single character")
        void shouldHandleSingleCharacter() {
            String result = processor.reverse("A");
            assertEquals("A", result);
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("should handle Null and Empty strings")
        void shouldHandleNullAndEmptyStrings(String input) {
            String result = processor.reverse(input);
            assertEquals("", result);
        }
    }
}
