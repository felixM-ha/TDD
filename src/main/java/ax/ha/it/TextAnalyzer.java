package ax.ha.it;

import java.util.*;

public class TextAnalyzer {

    private static final Set<String> POSITIVE_WORDS = Set.of(
            "good", "happy", "great", "excellent", "fantastic", "love", "fun", "easy"
    );
    private static final Set<String> NEGATIVE_WORDS = Set.of(
            "bad", "sad", "terrible", "horrible", "awful", "hate", "hard", "difficult"
    );

    public TextAnalyzer(Calculator calculator, StringProcessor processor) {
        // Konstruktor: dependencies lagras om de behövs senare
    }

    // ------------------- SENTIMENT ANALYSIS -------------------
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

    // ------------------- READABILITY ANALYSIS -------------------
    public ReadabilityResult analyzeReadability(String text) {
        int sentenceCount = countSentences(text);
        String[] words = text.split("\\s+");
        int wordCount = words.length;

        int syllableCount = countSyllables(words);
        double avgWordsPerSentence = (double) wordCount / sentenceCount;
        double avgSyllablesPerWord = (double) syllableCount / wordCount;

        double fleschScore = 206.835 - (1.015 * avgWordsPerSentence) - (84.6 * avgSyllablesPerWord);
        ReadingLevel level = determineReadingLevel(fleschScore);

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

    // ------------------- TEXT STATISTICS -------------------
    public int countSentences(String text) {
        if (text == null || text.isEmpty()) return 1;
        return text.split("[.!?]+").length;
    }

    public int countSyllables(String[] words) {
        int count = 0;
        for (String word : words) {
            count += countSyllablesInWord(word);
        }
        return count;
    }

    public int countSyllablesInWord(String word) {
        if (word == null || word.isEmpty()) return 1;
        word = word.toLowerCase().replaceAll("[^a-z]", "");
        int count = 0;
        boolean prevVowel = false;
        String vowels = "aeiouy";

        for (char c : word.toCharArray()) {
            boolean isVowel = vowels.indexOf(c) >= 0;
            if (isVowel && !prevVowel) count++;
            prevVowel = isVowel;
        }

        if (word.endsWith("e") && count > 1) count--;
        return Math.max(count, 1);
    }

    private ReadingLevel determineReadingLevel(double score) {
        if (score >= 90) return ReadingLevel.VERY_EASY;
        if (score >= 80) return ReadingLevel.EASY;
        if (score >= 70) return ReadingLevel.FAIRLY_EASY;
        if (score >= 60) return ReadingLevel.STANDARD;
        if (score >= 50) return ReadingLevel.FAIRLY_DIFFICULT;
        if (score >= 30) return ReadingLevel.DIFFICULT;
        return ReadingLevel.VERY_DIFFICULT;
    }

    // ------------------- TEXT COMPARISON / JACCARD -------------------
    public double calculateJaccardSimilarity(String text1, String text2) {
        List<String> list1 = Arrays.asList(text1.toLowerCase().split("\\s+"));
        List<String> list2 = Arrays.asList(text2.toLowerCase().split("\\s+"));

        Set<String> allWords = new HashSet<>();
        allWords.addAll(list1);
        allWords.addAll(list2);

        int intersectionCount = 0;
        int unionCount = 0;

        for (String word : allWords) {
            int count1 = Collections.frequency(list1, word);
            int count2 = Collections.frequency(list2, word);
            intersectionCount += Math.min(count1, count2);  // min = intersection
            unionCount += Math.max(count1, count2);        // max = union
        }

        return (double) intersectionCount / unionCount;
    }

}
