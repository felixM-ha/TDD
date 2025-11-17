import ax.ha.it.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Complex TextAnalyzer")
class TextAnalyzerTest {

    public TextAnalyzer analyzer;
    public Calculator calculator;
    public StringProcessor processor;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();      // dependency
        processor = new StringProcessor();  // dependency
        analyzer = new TextAnalyzer(calculator, processor); // inject dependencies
    }

    @Nested
    @DisplayName("Sentiment Analysis Tests")
    class SentimentAnalysisTests {

        @Test
        @DisplayName("Should analyze positive sentiment correctly")
        void shouldAnalyzePositiveSentimentCorrectly() {
            String text = "I love TDD! It makes coding fun and easy.";
            SentimentResult result = analyzer.analyzeSentiment(text);

            assertEquals(SentimentCategory.POSITIVE, result.getSentimentCategory());
            assertTrue(result.getSentimentScore() > 0);
            assertTrue(result.getPositiveWordCount() > 0);
        }

        @Test
        @DisplayName("Should analyze negative sentiment correctly")
        void shouldAnalyzeNegativeSentimentCorrectly() {
            String text = "I hate this hard and difficult task";
            SentimentResult result = analyzer.analyzeSentiment(text);

            assertEquals(SentimentCategory.NEGATIVE, result.getSentimentCategory());
            assertTrue(result.getSentimentScore() < 0);
            assertEquals(0, result.getPositiveWordCount());
            assertEquals(3, result.getNegativeWordCount());
        }

        @Test
        @DisplayName("Should analyze neutral sentiment correctly")
        void shouldAnalyzeNeutralSentimentCorrectly() {
            String text = "This task is just average and normal"; // inga ord i listorna
            SentimentResult result = analyzer.analyzeSentiment(text);

            assertEquals(SentimentCategory.NEUTRAL, result.getSentimentCategory());
            assertEquals(0, result.getPositiveWordCount());
            assertEquals(0, result.getNegativeWordCount());
            assertEquals(0.0, result.getSentimentScore());
        }

        @ParameterizedTest
        @CsvSource({
                "I love TDD! It makes coding fun and easy., POSITIVE",
                "I hate this hard and difficult task, NEGATIVE",
                "This task is neither easy nor difficult, NEUTRAL",
                "Coding challenges are great, POSITIVE",
                "Bugs and errors are horrible, NEGATIVE"
        })
        @DisplayName("Should classify sentiment categories correctly")
        void shouldClassifySentimentCategoriesCorrectly(String text, SentimentCategory expectedCategory) {
            SentimentResult result = analyzer.analyzeSentiment(text);
            assertEquals(expectedCategory, result.getSentimentCategory());
        }

        @Test
        @DisplayName("Should handle empty text in sentiment analysis")
        void shouldHandleEmptyTextInSentimentAnalysis() {
            // Test med tom sträng
            SentimentResult resultEmpty = analyzer.analyzeSentiment("");
            assertEquals(SentimentCategory.NEUTRAL, resultEmpty.getSentimentCategory());
            assertEquals(0, resultEmpty.getPositiveWordCount());
            assertEquals(0, resultEmpty.getNegativeWordCount());
            assertEquals(0.0, resultEmpty.getSentimentScore());

            // Test med null
            SentimentResult resultNull = analyzer.analyzeSentiment(null);
            assertEquals(SentimentCategory.NEUTRAL, resultNull.getSentimentCategory());
            assertEquals(0, resultNull.getPositiveWordCount());
            assertEquals(0, resultNull.getNegativeWordCount());
            assertEquals(0.0, resultNull.getSentimentScore());
        }
    }

    @Nested
    @DisplayName("Readability Analysis Tests")
    class ReadabilityAnalysisTests {

        @Test
        @DisplayName("Should calculate Flesch Reading Ease correctly")
        void ShouldCalculateFleschReadingEaseCorrectly() {

            String text = "This is a simple sentence. This is another simple one.";

            ReadabilityResult result = analyzer.analyzeReadability(text);

            // Förväntningar:
            // 2 meningar
            // 10 ord
            // ca 13 stavelser (skiljer beroende på algoritm → men testet ska vara RED nu)

            assertEquals(2, result.getSentenceCount());
            assertEquals(10, result.getWordCount());
            assertTrue(result.getSyllableCount() > 0);

            // Flesch score ska vara > 60 för denna text (relativt lättläst)
            assertTrue(result.getFleschScore() > 60);

            // Lättläst → STANDARD eller bättre
            assertNotNull(result.getReadingLevel());
        }

        @Test
        @DisplayName("Should classify reading levels correctly")
        void shouldClassifyReadingLevelsCorrectly() {
            String text = "This is a very easy sentence. Simple words are fun!";
            ReadabilityResult result = analyzer.analyzeReadability(text);

            // Vi förväntar oss VERY_EASY eftersom texten är kort och enkel
            assertEquals(ReadingLevel.VERY_EASY, result.getReadingLevel());
            assertTrue(result.getFleschScore() > 90);
        }

        @Test
        @DisplayName("Should handle complex sentences correctly")
        void shouldHandleComplexSentencesCorrectly() {
            String text = "The implementation of multifaceted algorithms requires substantial cognitive effort and meticulous attention to syntactic detail.";
            ReadabilityResult result = analyzer.analyzeReadability(text);

            // Vi förväntar oss VERY_DIFFICULT för komplex akademisk text
            assertEquals(ReadingLevel.VERY_DIFFICULT, result.getReadingLevel());
            assertTrue(result.getFleschScore() < 30);
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "Simple text for testing readability.",
                "Another example sentence to check consistency.",
                "Yet another sentence to ensure the metrics are stable."
        })
        @DisplayName("Should provide consistent readability metrics")
        void shouldProvideConsistentReadabilityMetrics(String text) {
            ReadabilityResult firstResult = analyzer.analyzeReadability(text);
            ReadabilityResult secondResult = analyzer.analyzeReadability(text);

            // Kontrollera att resultaten är identiska för samma input
            assertEquals(firstResult.getFleschScore(), secondResult.getFleschScore(), 0.001);
            assertEquals(firstResult.getReadingLevel(), secondResult.getReadingLevel());
            assertEquals(firstResult.getWordCount(), secondResult.getWordCount());
            assertEquals(firstResult.getSentenceCount(), secondResult.getSentenceCount());
            assertEquals(firstResult.getSyllableCount(), secondResult.getSyllableCount());
        }
    }

    @Nested
    @DisplayName("Statistical Analysis Tests")
    class StatisticalAnalysisTests {

        @Test
        @DisplayName("Should generate comprehensive text statistics")
        void shouldGenerateComprehensiveTextStatistics() {
            String text = "This is a simple sentence. Here is another one! " +
                    "Complex sentences, however, might take longer to read.";

            // Kör läsbarhetsanalysen
            ReadabilityResult result = analyzer.analyzeReadability(text);

            // Verifiera att alla metrics är beräknade
            assertNotNull(result);
            assertTrue(result.getSentenceCount() > 0, "Sentence count should be positive");
            assertTrue(result.getWordCount() > 0, "Word count should be positive");
            assertTrue(result.getSyllableCount() > 0, "Syllable count should be positive");
            assertTrue(result.getAvgWordsPerSentence() > 0, "Average words per sentence should be positive");
            assertTrue(result.getAvgSyllablesPerWord() > 0, "Average syllables per word should be positive");
            assertTrue(result.getFleschScore() >= 0, "Flesch score should be non-negative");
            assertNotNull(result.getReadingLevel(), "Reading level should not be null");

            // Extra: Du kan skriva ut för visuell kontroll (valfritt)
            System.out.println("Sentences: " + result.getSentenceCount());
            System.out.println("Words: " + result.getWordCount());
            System.out.println("Syllables: " + result.getSyllableCount());
            System.out.println("Avg words/sentence: " + result.getAvgWordsPerSentence());
            System.out.println("Avg syllables/word: " + result.getAvgSyllablesPerWord());
            System.out.println("Flesch score: " + result.getFleschScore());
            System.out.println("Reading level: " + result.getReadingLevel());
        }

        @Test
        @DisplayName("Should calculate basic text statistics")
        void shouldCalculateBasicTextStatistics() {
            String text = "Hello world! Hello TDD. This is a simple test.";

            // Räkna ord
            String[] words = text.toLowerCase().replaceAll("[^a-z\\s]", "").split("\\s+");
            int wordCount = words.length;

            // Räkna unika ord
            Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
            int uniqueWordCount = uniqueWords.size();

            // Assertions
            assertEquals(9, wordCount, "Word count should match");
            assertEquals(8, uniqueWordCount, "Unique word count should match");
        }

        @Test
        @DisplayName("Should count spaces in text")
        void shouldCountSpacesInText() {
            String text = "Hello world! This is a test with spaces.";

            // Räkna mellanslag
            long spaceCount = text.chars().filter(ch -> ch == ' ').count();

            // Assertions
            assertEquals(7, spaceCount, "Space count should match");
        }

    }

}