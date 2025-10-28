import ax.ha.it.MoneyConverter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyConverterTest {

    @Test
    void shouldConvertUsdToEur() {
        // Arrange
        MoneyConverter converter = new MoneyConverter();
        BigDecimal usd = new BigDecimal("100.00");

        // Act
        BigDecimal result = converter.usdToEur(usd);

        // Assert
        assertThat(result).isEqualByComparingTo(new BigDecimal("92.00"));
    }
}
