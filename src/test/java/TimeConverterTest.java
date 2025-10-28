import ax.ha.it.TimeConverter;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TimeConverterTest {

    private final TimeConverter converter = new TimeConverter();

    @Nested
    class SecondsToMinutes {

        @Test
        void shouldConvert60SecondsTo1Minute() {
            assertThat(converter.secondsToMinutes(60)).isEqualTo(1.0);
        }

        @Test
        void shouldConvert0SecondsTo0Minutes() {
            assertThat(converter.secondsToMinutes(0)).isEqualTo(0.0);
        }

        @Test
        void shouldThrowExceptionForNegativeSeconds() {
            assertThatThrownBy(() -> converter.secondsToMinutes(-10))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Seconds cannot be negative");
        }
    }
}
