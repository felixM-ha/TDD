package ax.ha.it;

import java.util.Set;

public class TextAnalyzer {

    private static final Set<String> POSITIVE_WORDS = Set.of("good", "happy", "great", "excellent", "fantastic", "love", "fun", "easy");
    private static final Set<String> NEGATIVE_WORDS = Set.of("bad", "sad", "terrible", "horrible", "awful", "hate", "hard", "difficult");

    public TextAnalyzer(Calculator calculator, StringProcessor processor) {
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

    public ReadabilityResult analyzeReadability(String text) {

        // Räkna meningar (testet använder punkt)
        String[] sentences = text.split("[.!?]+");
        int sentenceCount = sentences.length;

        // Räkna ord
        String[] words = text.split("\\s+");
        int wordCount = words.length;

        // Räkna stavelser (enkel version, räcker för testet)
        int syllableCount = 0;
        for (String word : words) {
            syllableCount += countSyllablesInWord(word);
        }

        double avgWordsPerSentence = (double) wordCount / sentenceCount;
        double avgSyllablesPerWord = (double) syllableCount / wordCount;

        double fleschScore =
                206.835
                        - (1.015 * avgWordsPerSentence)
                        - (84.6 * avgSyllablesPerWord);

        ReadingLevel level = classifyReadingLevel(fleschScore);

        return new ReadabilityResult(
                fleschScore,
                level,
                sentenceCount,
                wordCount,
                syllableCount,
                avgWordsPerSentence,
                avgSyllablesPerWord
        );

    }

    private int countSyllablesInWord(String word) {
        if (word == null || word.isEmpty()) return 1; // enkel fallback

        word = word.toLowerCase().replaceAll("[^a-z]", "");

        int count = 0;
        boolean prevVowel = false;
        String vowels = "aeiouy";

        for (char c : word.toCharArray()) {
            boolean isVowel = vowels.indexOf(c) >= 0;
            if (isVowel && !prevVowel) {
                count++;
            }
            prevVowel = isVowel;
        }

        // Ta bort tyst slut-e (om det finns och vi räknat mer än 1)
        if (word.endsWith("e") && count > 1) {
            count--;
        }

        return Math.max(count, 1);
    }

    private ReadingLevel classifyReadingLevel(double score) {
        if (score >= 90) return ReadingLevel.VERY_EASY;
        if (score >= 80) return ReadingLevel.EASY;
        if (score >= 70) return ReadingLevel.FAIRLY_EASY;
        if (score >= 60) return ReadingLevel.STANDARD;
        if (score >= 50) return ReadingLevel.FAIRLY_DIFFICULT;
        if (score >= 30) return ReadingLevel.DIFFICULT;
        return ReadingLevel.VERY_DIFFICULT;
    }

}
