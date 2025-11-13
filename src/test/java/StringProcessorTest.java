import ax.ha.it.StringProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

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

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("should return zero for null, empty, or whitespace-only strings")
        @ValueSource(strings = {"   ", "\t", "\n"})
        void shouldReturnZeroForNullEmptyOrWhitespaceOnlyStrings(String input) {
            int count = processor.countWords(input);
            assertEquals(0, count);
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

    @Nested
    @DisplayName("Advanced Validation Tests")
    class AdvancedValidationTests {

        @Test
        @DisplayName("should validate US phone number format")
        void shouldValidateUSPhoneNumberFormat() {
            boolean result = processor.isValidUSPhoneNumber("(123) 456-7890");
            assertTrue(result);
        }

        @Test
        @DisplayName("should reject invalid phone number formats")
        void shouldRejectInvalidPhoneNumberFormats() {
            assertFalse(processor.isValidUSPhoneNumber("123-456-7890"));
            assertFalse(processor.isValidUSPhoneNumber("1234567890"));
            assertFalse(processor.isValidUSPhoneNumber("(123)456-7890")); // saknar space
            assertFalse(processor.isValidUSPhoneNumber("(12) 345-6789")); // för få siffror
            assertFalse(processor.isValidUSPhoneNumber(null));
            assertFalse(processor.isValidUSPhoneNumber(""));
        }

        @Test
        @DisplayName("should validate URL format")
        void shouldValidateURLFormat() {
            boolean result = processor.isValidURL("https://www.example.com");
            assertTrue(result);
        }

        @Test
        @DisplayName("should reject invalid URL formats")
        void shouldRejectInvalidURLFormats() {
            assertFalse(processor.isValidURL("htp://example.com"));  // fel protokoll
            assertFalse(processor.isValidURL("www.example.com"));    // saknar http/https
            assertFalse(processor.isValidURL("example"));            // saknar domän
            assertFalse(processor.isValidURL(""));                   // tom sträng
            assertFalse(processor.isValidURL(null));                 // null
        }
    }

    @Nested
    @DisplayName("Performance and Edge Case Tests")
    class PerformanceAndEdgeCaseTests {

        @Test
        @DisplayName("Should handle very long strings in reversal")
        void shouldHandleVeryLongStringsInReversal() {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 100_000; i++) {
                sb.append("a");
            }
            String longString = sb.toString();

            String reversed = processor.reverse(longString);

            assertEquals(longString.length(), reversed.length());

            assertTrue(reversed.chars().allMatch(c -> c == 'a'));
        }

        @Test
        @DisplayName("Should handle strings with special characters")
        void shouldHandleStringsWithSpecialCharacters() {
            String input = "Hello, World! @2025 #TDD";
            String expected = "DDT# 5202@ !dlroW ,olleH";

            String result = processor.reverse(input);

            assertEquals(expected, result);
        }

        @Test
        @DisplayName("Should handle unicode characters")
        void shouldHandleUnicodeCharacters() {
            String input = "こんにちは世界🌏";
            String expected = "🌏界世はちにんこ";

            String result = processor.reverse(input);

            assertEquals(expected, result);
        }

        @Test
        @DisplayName("Should handle very long palindrome check")
        void shouldHandleVeryLongPalindromeCheck() {
            // Skapa en lång palindrom-sträng
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 50_000; i++) {
                sb.append("abc");
            }
            String longPalindrome = sb.toString() + sb.reverse().toString();

            boolean result = processor.isPalindrome(longPalindrome);

            assertTrue(result);
        }

        @Test
        @DisplayName("Should handle word count with various whitespace")
        void shouldHandleWordCountWithVariousWhitespace() {
            String input = "  Hello   world \t from\nTDD  ";
            int count = processor.countWords(input);
            assertEquals(4, count);
        }

    }

}
