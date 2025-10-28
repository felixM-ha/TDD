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
        return Math.round(minutes * SECONDS_PER_MINUTE) * 1.0;
    }

    public double minutesToHours(double minutes) {
        validateNonNegative(minutes, "Minutes");
        return minutes / 60;
    }

    public double hoursToMinutes(double hours) {
        validateNonNegative(hours, "Hours");
        return Math.round(hours * SECONDS_PER_MINUTE) * 1.0;
    }

    public double hoursToDays(double hours) {
        validateNonNegative(hours, "Hours");
        return hours / 24;
    }

    public double daysToHours(double days) {
        validateNonNegative(days, "Days");
        return days / 24;
    }

    public String formatAsHHMMSS(int totalSeconds) {
        validateNonNegative(totalSeconds, "Total seconds");

        int hours = totalSeconds / 3600;
        int minutes = totalSeconds % 3600 / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

}
