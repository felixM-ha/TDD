package ax.ha.it;

public class TimeConverter {

    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int HOURS_PER_DAY = 24;
    private static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    // ==================== Seconds ↔ Minutes ====================
    public double secondsToMinutes(double seconds) {
        validateNonNegative(seconds, "Seconds");
        return seconds / SECONDS_PER_MINUTE;
    }

    public double minutesToSeconds(double minutes) {
        validateNonNegative(minutes, "Minutes");
        return Math.round(minutes * SECONDS_PER_MINUTE) * 1.0;
    }

    // ==================== Minutes ↔ Hours ====================
    public double minutesToHours(double minutes) {
        validateNonNegative(minutes, "Minutes");
        return minutes / MINUTES_PER_HOUR;
    }

    public double hoursToMinutes(double hours) {
        validateNonNegative(hours, "Hours");
        return Math.round(hours * MINUTES_PER_HOUR) * 1.0;
    }

    // ==================== Hours ↔ Days ====================
    public double hoursToDays(double hours) {
        validateNonNegative(hours, "Hours");
        return hours / HOURS_PER_DAY;
    }

    public double daysToHours(double days) {
        validateNonNegative(days, "Days");
        return days * HOURS_PER_DAY;
    }

    // ==================== Time Formatting ====================
    public String formatAsHHMMSS(long totalSeconds) {
        validateNonNegative(totalSeconds, "Seconds");

        long hours = totalSeconds / SECONDS_PER_HOUR;
        long remainingSeconds = totalSeconds % SECONDS_PER_HOUR;
        long minutes = remainingSeconds / SECONDS_PER_MINUTE;
        long seconds = remainingSeconds % SECONDS_PER_MINUTE;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    // ==================== Time Parsing ====================
    public long parseHHMMSS(String time) {
        if (time == null) throw new IllegalArgumentException("Input cannot be null");

        String[] parts = time.split(":");
        if (parts.length != 3) throw new IllegalArgumentException("Invalid time format");

        try {
            long hours = Long.parseLong(parts[0]);
            long minutes = Long.parseLong(parts[1]);
            long seconds = Long.parseLong(parts[2]);

            if (hours < 0 || minutes < 0 || seconds < 0)
                throw new IllegalArgumentException("Time values cannot be negative");

            return hours * SECONDS_PER_HOUR + minutes * SECONDS_PER_MINUTE + seconds;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid time format", e);
        }
    }

    // ==================== Validation ====================
    private void validateNonNegative(double value, String name) {
        if (value < 0) {
            throw new IllegalArgumentException(name + " cannot be negative");
        }
    }

    private void validateNonNegative(long value, String name) {
        if (value < 0) {
            throw new IllegalArgumentException(name + " cannot be negative");
        }
    }
}
