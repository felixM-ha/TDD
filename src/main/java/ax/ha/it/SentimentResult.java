package ax.ha.it;

public class SentimentResult {

    private final double sentimentScore;
    private final SentimentCategory sentimentCategory;
    private final int positiveWordCount;
    private final int negativeWordCount;

    public SentimentResult(double sentimentScore,
                           SentimentCategory sentimentCategory,
                           int positiveWordCount,
                           int negativeWordCount) {
        this.sentimentScore = sentimentScore;
        this.sentimentCategory = sentimentCategory;
        this.positiveWordCount = positiveWordCount;
        this.negativeWordCount = negativeWordCount;
    }

    public double getSentimentScore() {
        return sentimentScore;
    }

    public SentimentCategory getSentimentCategory() {
        return sentimentCategory;
    }

    public int getPositiveWordCount() {
        return positiveWordCount;
    }

    public int getNegativeWordCount() {
        return negativeWordCount;
    }
}
