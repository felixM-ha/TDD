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

    @Nested
    @DisplayName("String Compression Tests")
    class StringCompressionTests {

        @Test
        @DisplayName("should compress repeated characters")
        void shouldCompressRepeatedCharacters() {
            String result = processor.compress("aaabbccccd");
            assertEquals("a3b2c4d1", result);
        }

        @Test
        @DisplayName("should return original if compression doesn't reduce length")
        void shouldReturnOriginalIfCompressionDoesNotReduceLength() {
            String result = processor.compress("abc");
            assertEquals("abc", result);
        }

        @Test
        @DisplayName("should handle single character")
        void shouldHandleSingleCharacter() {
            String result = processor.compress("A");
            assertEquals("A", result);
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("should handle null and empty strings in compression")
        void shouldHandleNullAndEmptyStringsInCompression(String input) {
            String result = processor.compress(input);
            assertEquals("", result);
        }
    }

    @Nested
    @DisplayName("Word Count Tests")
    class WordCountTests {

        @Test
        @DisplayName("should count words in simple sentence")
        void shouldCountWordsInSimpleSentence() {
            int count = processor.countWords("Hello world from TDD");
            assertEquals(4, count);
        }

        @Test
        @DisplayName("should handle multiple spaces between words")
        void shouldHandleMultipleSpacesBetweenWords() {
            int count = processor.countWords("Hello   world    from   TDD");
            assertEquals(4, count);
        }

        @Test
        @DisplayName("should handle leading and trailing spaces")
        void shouldHandleLeadingAndTrailingSpaces() {
            int count = processor.countWords("   Hello world from TDD   ");
            assertEquals(4, count);
        }

        @Test
        @DisplayName("should count single word")
        void shouldCountSingleWord() {
            int count = processor.countWords("Hello");
            assertEquals(1, count);
        }
    }

    @Nested
    @DisplayName("Input Validation Tests")
    class InputValidationTests {

        @Test
        @DisplayName("should validate email format")
        void shouldValidateEmailFormat() {
            boolean result = processor.isValidEmail("test@email.com");
            assertTrue(result);
        }

        @Test
        @DisplayName("should reject invalid email formats")
        void shouldRejectInvalidEmailFormats() {
            assertFalse(processor.isValidEmail("plainaddress"));
            assertFalse(processor.isValidEmail("missing@domain"));
            assertFalse(processor.isValidEmail("@missingusername.com"));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("should reject null and empty emails")
        void shouldRejectNullAndEmptyEmails(String input) {
            assertFalse(processor.isValidEmail(input));
        }
    }

    @Nested
    @DisplayName("String Manipulation Tests")
    class StringManipulationTests {

        @Test
        @DisplayName("should capitalize first letter of each word")
        void shouldCapitalizeFirstLetterOfEachWord() {
            String result = processor.capitalizeWords("hello world from tdd");
            assertEquals("Hello World From Tdd", result);
        }
    }

    @Test
    @DisplayName("should handle single word capitalization")
    void shouldHandleSingleWordCapitalization() {
        String result = processor.capitalizeWords("hello");
        assertEquals("Hello", result);
    }

    @Test
    @DisplayName("should handle mixed case input in capitalization")
    void shouldHandleMixedCaseInputInCapitalization() {
        String result = processor.capitalizeWords("hElLo wORld fRoM tDd");
        assertEquals("Hello World From Tdd", result);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("should handle null and empty strings in capitalization")
    void shouldHandleNullAndEmptyStringsInCapitalization(String input) {
        String result = processor.capitalizeWords(input);
        assertEquals("", result);
    }

    @Test
    @DisplayName("should check if string contains substring")
    void shouldCheckIfStringContainsSubstring() {
        boolean result = processor.containsSubstring("Hello World", "World");
        assertTrue(result);
    }

    @Test
    @DisplayName("should check case-insensitive substring")
    void shouldCheckCaseInsensitiveSubstring() {
        boolean result = processor.containsSubstringIgnoreCase("Hello World", "world");
        assertTrue(result);
    }

    @Test
    @DisplayName("should return false for non-existent substring")
    void shouldReturnFalseForNonExistentSubstring() {
        boolean result = processor.containsSubstring("Hello World", "Java");
        assertFalse(result);
    }

    @Test
    @DisplayName("should remove duplicate characters")
    void shouldRemoveDuplicateCharacters() {
        String result = processor.removeDuplicateCharacters("aabbcc");
        assertEquals("abc", result);
    }

    @Test
    @DisplayName("should handle string with no duplicates")
    void shouldHandleStringWithNoDuplicates() {
        String result = processor.removeDuplicateCharacters("abc");
        assertEquals("abc", result);
    }

    @Test
    @DisplayName("should preserve order when removing duplicates")
    void shouldPreserveOrderWhenRemovingDuplicates() {
        String result = processor.removeDuplicateCharacters("banana");
        assertEquals("ban", result);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("should handle null and empty strings in duplicate removal")
    void shouldHandleNullAndEmptyStringsInDuplicateRemoval(String input) {
        String result = processor.removeDuplicateCharacters(input);
        assertEquals("", result);
    }

}
