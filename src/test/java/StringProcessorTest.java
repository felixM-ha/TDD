import ax.ha.it.StringProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

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

        @Test
        @DisplayName("should preserve spaces in reversal")
        void shouldPreserveSpacesInReversal() {
            String result = processor.reverse("Hello World");
            assertEquals("dlroW olleH", result);
        }
    }

    @Nested
    @DisplayName("Palindrome Detection Tests")
    class PalindromeDetectionTests {

        @Test
        @DisplayName("should detect simple palindrome")
        void shouldDetectSimplePalindrome() {
            boolean result = processor.isPalindrome("level");
            assertTrue(result);
        }

        @Test
        @DisplayName("should detect non-palindrome")
        void shouldDetectNonPalindrome() {
            boolean result = processor.isPalindrome("hello");
            assertFalse(result);
        }

        @Test
        @DisplayName("should handle case-insensitive palindromes")
        void shouldHandleCaseInsensitivePalindrome() {
            boolean result = processor.isPalindrome("LeVeL");
            assertTrue(result);
        }

        @Test
        @DisplayName("should handle palindromes with spaces")
        void shouldHandlePalindromesWithSpaces() {
            boolean result = processor.isPalindrome("nurses run");
            assertTrue(result);
        }

        @Test
        @DisplayName("should handle single character as palindrome")
        void shouldHandleSingleCharacterAsPalindrome() {
            boolean result = processor.isPalindrome("A");
            assertTrue(result);
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("should handle null and empty as palindromes")
        void shouldHandleNullAndEmptyAsPalindromes(String input) {
            boolean result = processor.isPalindrome(input);
            assertTrue(result);
        }
    }
}
