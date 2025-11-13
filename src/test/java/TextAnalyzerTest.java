import ax.ha.it.Calculator;
import ax.ha.it.SentimentCategory;
import ax.ha.it.SentimentResult;
import ax.ha.it.StringProcessor;
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

            // Förväntningar på analysen
            assertEquals(SentimentCategory.POSITIVE, result.getSentimentCategory());
            assertTrue(result.getSentimentScore() > 0);
            assertTrue(result.getPositiveWordCount() > 0);
        }
    }
}