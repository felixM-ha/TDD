import ax.ha.it.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
            String text = "This task is neither easy nor difficult";
            SentimentResult result = analyzer.analyzeSentiment(text);

            assertEquals(SentimentCategory.NEUTRAL, result.getSentimentCategory());
            assertEquals(0, result.getPositiveWordCount());
            assertEquals(0, result.getNegativeWordCount());
            assertEquals(0.0, result.getSentimentScore());
        }

    }
}