import ax.ha.it.TimeConverter;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TimeConverterTest {

    private final TimeConverter converter = new TimeConverter();

    // --- Seconds to Minutes Conversion ---
    @Nested
    class SecondsToMinutes {

        @Test
        void shouldConvert60SecondsTo1Minute() {
            assertThat(converter.secondsToMinutes(60)).isCloseTo(1.0, within(0.0001));
        }

        @Test
        void shouldConvert120SecondsTo2Minutes() {
            assertThat(converter.secondsToMinutes(120)).isCloseTo(2.0, within(0.0001));
        }

        @Test
        void shouldConvert90SecondsTo1Point5Minutes() {
            assertThat(converter.secondsToMinutes(90)).isCloseTo(1.5, within(0.0001));
        }

        @Test
        void shouldThrowExceptionForNegativeSeconds() {
            assertThatThrownBy(() -> converter.secondsToMinutes(-10))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // --- Minutes to Seconds Conversion ---
    @Nested
    class MinutesToSecondsConversion {

        @Test
        void shouldConvert1MinuteTo60Seconds() {
            assertThat(converter.minutesToSeconds(1.0)).isCloseTo(60.0, within(0.0001));
        }

        @Test
        void shouldConvert2Point5MinutesTo150Seconds() {
            assertThat(converter.minutesToSeconds(2.5)).isCloseTo(150.0, within(0.0001));
        }

        @Test
        void shouldMaintainValueThroughRoundTrip() {
            double minutes = 2.5;
            double seconds = converter.minutesToSeconds(minutes);
            double minutesBack = converter.secondsToMinutes(seconds);
            assertThat(minutesBack).isCloseTo(minutes, within(0.0001));
        }
    }

    // --- Minutes to Hours Conversion ---
    @Nested
    class MinutesToHoursConversion {

        
        @Test
        void shouldConvert60MinutesTo1Hour() {
            assertThat(converter.minutesToHours(60.0)).isCloseTo(1.0, within(0.0001));
        }

        @Test
        void shouldConvert120MinutesTo2Hours() {
            assertThat(converter.minutesToHours(120.0)).isCloseTo(2.0, within(0.0001));
        }

        @Test
        void shouldConvert90MinutesTo1Point5Hours() {
            assertThat(converter.minutesToHours(90.0)).isCloseTo(1.5, within(0.0001));
        }
    }

    // --- Hours to Minutes Conversion ---
    @Nested
    class HoursToMinutesConversion {

        @Test
        void shouldConvert1HourTo60Minutes() {
            assertThat(converter.hoursToMinutes(1.0)).isCloseTo(60.0, within(0.0001));
        }

        @Test
        void shouldConvert2Point5HoursTo150Minutes() {
            assertThat(converter.hoursToMinutes(2.5)).isCloseTo(150.0, within(0.0001));
        }

        @Test
        void shouldMaintainValueThroughRoundTrip() {
            double hours = 2.5;
            double minutes = converter.hoursToMinutes(hours);
            double hoursBack = converter.minutesToHours(minutes);
            assertThat(hoursBack).isCloseTo(hours, within(0.0001));
        }
    }

    // --- Hours to Days Conversion ---
    @Nested
    class HoursToDaysConversion {

        @Test
        void shouldConvert24HoursTo1Day() {
            assertThat(converter.hoursToDays(24.0)).isCloseTo(1.0, within(0.0001));
        }

        @Test
        void shouldConvert48HoursTo2Days() {
            assertThat(converter.hoursToDays(48.0)).isCloseTo(2.0, within(0.0001));
        }

        @Test
        void shouldConvert12HoursToHalfDay() {
            assertThat(converter.hoursToDays(12.0)).isCloseTo(0.5, within(0.0001));
        }
    }

    // --- Days to Hours Conversion ---
    @Nested
    class DaysToHoursConversion {

        @Test
        void shouldConvert1DayTo24Hours() {
            assertThat(converter.daysToHours(1.0)).isCloseTo(24.0, within(0.0001));
        }

        @Test
        void shouldConvert7DaysTo168Hours() {
            assertThat(converter.daysToHours(7.0)).isCloseTo(168.0, within(0.0001));
        }
    }

    // --- Time Formatting as HH:MM:SS ---
    @Nested
    class TimeFormatting {

        @Test
        void shouldFormat3661SecondsAs010101() {
            assertThat(converter.formatAsHHMMSS(3661)).isEqualTo("01:01:01");
        }

        @Test
        void shouldFormat3600SecondsAs010000() {
            assertThat(converter.formatAsHHMMSS(3600)).isEqualTo("01:00:00");
        }

        @Test
        void shouldFormat90SecondsAs000130() {
            assertThat(converter.formatAsHHMMSS(90)).isEqualTo("00:01:30");
        }

        @Test
        void shouldFormat0SecondsAs000000() {
            assertThat(converter.formatAsHHMMSS(0)).isEqualTo("00:00:00");
        }
    }

    // --- Time Parsing from HH:MM:SS ---
    @Nested
    class TimeParsing {

        @Test
        void shouldParse010101To3661Seconds() {
            assertThat(converter.parseHHMMSS("01:01:01")).isEqualTo(3661);
        }

        @Test
        void shouldParse010000To3600Seconds() {
            assertThat(converter.parseHHMMSS("01:00:00")).isEqualTo(3600);
        }

        @Test
        void shouldParse000130To90Seconds() {
            assertThat(converter.parseHHMMSS("00:01:30")).isEqualTo(90);
        }

        @Test
        void shouldMaintainValueThroughRoundTrip() {
            int seconds = 3661;
            String formatted = converter.formatAsHHMMSS(seconds);
            int parsed = converter.parseHHMMSS(formatted);
            assertThat(parsed).isEqualTo(seconds);
        }

        @Test
        void shouldThrowExceptionForNullInput() {
            assertThatThrownBy(() -> converter.parseHHMMSS(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void shouldThrowExceptionForInvalidFormat() {
            assertThatThrownBy(() -> converter.parseHHMMSS("invalid"))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

}
