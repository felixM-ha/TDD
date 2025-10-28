import ax.ha.it.MoneyConverter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyConverterTest {

    @Test
    void shouldConvertUsdToEur() {
        MoneyConverter converter = new MoneyConverter();
        BigDecimal usd = new BigDecimal("100.00");

        BigDecimal result = converter.usdToEur(usd);

        assertThat(result).isEqualByComparingTo(new BigDecimal("92.00"));
    }

    @Test
    void shouldConvertEurToUsd() {
        MoneyConverter converter = new MoneyConverter();
        BigDecimal eur = new BigDecimal("92.00");

        BigDecimal result = converter.eurToUsd(eur);

        assertThat(result).isEqualByComparingTo(new BigDecimal("100.00"));
    }

    @Test
    void shouldConvertUsdToSek() {
        MoneyConverter converter = new MoneyConverter();
        BigDecimal usd = new BigDecimal("100.00");

        BigDecimal result = converter.usdToSek(usd);

        assertThat(result).isEqualByComparingTo(new BigDecimal("1050.00"));
    }

    @Test
    void shouldConvertSekToUsd() {
        MoneyConverter converter = new MoneyConverter();
        BigDecimal sek = new BigDecimal("1050.00");

        BigDecimal result = converter.sekToUsd(sek);

        assertThat(result).isEqualByComparingTo(new BigDecimal("100.00"));
    }
}
