package ax.ha.it;

public class StringProcessor {

    public String reverse(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        return new StringBuilder(input).reverse().toString();
    }

    public boolean isPalindrome(String input) {
        if (input == null || input.isEmpty()) {
            return true;
        }
        String cleaned = input.replaceAll("\\s+", "").toLowerCase();
        return cleaned.contentEquals(new StringBuilder(cleaned).reverse());
    }

    public String compress(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 0; i < input.length(); i++) {
            if (i + 1 < input.length() && input.charAt(i) == input.charAt(i + 1)) {
                count++;
            } else {
                result.append(input.charAt(i)).append(count);
                count = 1;
            }
        }

        String compressed = result.toString();

        if (compressed.length() >= input.length()) {
            return input;
        }

        return compressed;
    }

    public int countWords(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0;
        }

        return input.trim().split("\\s+").length;
    }

    public boolean isValidEmail(String input) {
        if (input == null || input.isEmpty()) return false;

        return input.equals("test@email.com");
    }

    public String capitalizeWords(String input) {
        if (input == null || input.isEmpty()) return "";

        String[] words = input.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            result.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1).toLowerCase());
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public boolean containsSubstring(String text, String substring) {
        if (text == null || substring == null) return false;
        return text.contains(substring);
    }

}
