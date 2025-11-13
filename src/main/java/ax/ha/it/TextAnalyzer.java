package ax.ha.it;

import java.util.Set;

public class TextAnalyzer {

    private final Calculator calculator;
    private final StringProcessor processor;

    private static final Set<String> POSITIVE_WORDS = Set.of("good", "happy", "great", "excellent", "fantastic", "love", "fun", "easy");
    private static final Set<String> NEGATIVE_WORDS = Set.of("bad", "sad", "terrible", "horrible", "awful", "hate", "hard", "difficult");

    public TextAnalyzer(Calculator calculator, StringProcessor processor) {
        this.calculator = calculator;
        this.processor = processor;
    }

    public SentimentResult analyzeSentiment(String text) {
        if (text == null || text.isEmpty()) {
            return new SentimentResult(0.0, SentimentCategory.NEUTRAL, 0, 0);
        }

        String[] words = text.toLowerCase().split("\\s+");
        int positiveCount = 0;
        int negativeCount = 0;

        for (String word : words) {
            for (String positive : POSITIVE_WORDS) {
                if (word.startsWith(positive)) {
                    positiveCount++;
                    break;
                }
            }
            for (String negative : NEGATIVE_WORDS) {
                if (word.startsWith(negative)) {
                    negativeCount++;
                    break;
                }
            }
        }

        double score = positiveCount - negativeCount;
        SentimentCategory category;

        if (score > 0) category = SentimentCategory.POSITIVE;
        else if (score < 0) category = SentimentCategory.NEGATIVE;
        else category = SentimentCategory.NEUTRAL;

        return new SentimentResult(score, category, positiveCount, negativeCount);
    }

}
