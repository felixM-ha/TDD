package ax.ha.it;

import java.util.Set;

public class TextAnalyzer {

    private final Calculator calculator;
    private final StringProcessor processor;

    private static final Set<String> POSITIVE_WORDS = Set.of("love", "fun", "easy", "good", "great");
    private static final Set<String> NEGATIVE_WORDS = Set.of("hate", "hard", "difficult", "bad");

    public TextAnalyzer(Calculator calculator, StringProcessor processor) {
        this.calculator = calculator;
        this.processor = processor;
    }

    public SentimentResult analyzeSentiment(String text) {
        if (text == null || text.isEmpty()) {
            return new SentimentResult(0, SentimentCategory.NEUTRAL, 0, 0);
        }

        String[] words = text.toLowerCase().split("\\s+");
        int positiveCount = 0;
        int negativeCount = 0;

        for (String word : words) {
            if (POSITIVE_WORDS.contains(word)) positiveCount++;
            if (NEGATIVE_WORDS.contains(word)) negativeCount++;
        }

        double score = positiveCount - negativeCount;
        SentimentCategory category = score > 0 ? SentimentCategory.POSITIVE
                : score < 0 ? SentimentCategory.NEGATIVE
                : SentimentCategory.NEUTRAL;

        return new SentimentResult(score, category, positiveCount, negativeCount);
    }
}
