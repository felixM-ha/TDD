package ax.ha.it;

public class StringProcessor {

    public String reverse(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        return new StringBuilder(input).reverse().toString();
    }
}
