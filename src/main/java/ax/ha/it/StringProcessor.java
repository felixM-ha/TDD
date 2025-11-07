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
        String lower = input.toLowerCase();
        return lower.equals(new StringBuilder(lower).reverse().toString());
    }
}
