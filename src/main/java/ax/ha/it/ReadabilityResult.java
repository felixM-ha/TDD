package ax.ha.it;

public class ReadabilityResult {

    private final double fleschScore;
    private final ReadingLevel readingLevel;

    private final int sentenceCount;
    private final int wordCount;
    private final int syllableCount;

    private final double avgWordsPerSentence;
    private final double avgSyllablesPerWord;

    public ReadabilityResult(double fleschScore,
                             ReadingLevel readingLevel,
                             int sentenceCount,
                             int wordCount,
                             int syllableCount,
                             double avgWordsPerSentence,
                             double avgSyllablesPerWord) {

        this.fleschScore = fleschScore;
        this.readingLevel = readingLevel;
        this.sentenceCount = sentenceCount;
        this.wordCount = wordCount;
        this.syllableCount = syllableCount;
        this.avgWordsPerSentence = avgWordsPerSentence;
        this.avgSyllablesPerWord = avgSyllablesPerWord;
    }

    public double getFleschScore() {
        return fleschScore;
    }

    public ReadingLevel getReadingLevel() {
        return readingLevel;
    }

    public int getSentenceCount() {
        return sentenceCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getSyllableCount() {
        return syllableCount;
    }

    public double getAvgWordsPerSentence() {
        return avgWordsPerSentence;
    }

    public double getAvgSyllablesPerWord() {
        return avgSyllablesPerWord;
    }
}
