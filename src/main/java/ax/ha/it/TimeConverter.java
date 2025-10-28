package ax.ha.it;

public class TimeConverter {

    private static final int SECONDS_PER_MINUTE = 60;

    public double secondsToMinutes(double seconds) {
        validateNonNegative(seconds, "Seconds");
        return seconds / SECONDS_PER_MINUTE;
    }

    private void validateNonNegative(double value, String name) {
        if (value < 0) {
            throw new IllegalArgumentException(name + " cannot be negative");
        }
    }

    public double minutesToSeconds(double minutes) {
        validateNonNegative(minutes, "Minutes");
        return minutes * 60;
    }


}
