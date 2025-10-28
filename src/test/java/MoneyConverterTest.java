import ax.ha.it.MoneyConverter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyConverterTest {

    private final MoneyConverter converter = new MoneyConverter();

    // --- USD → EUR ---
    @Test
    void shouldConvertUsdToEur() {
        assertThat(converter.usdToEur(new BigDecimal("100.00"))).isEqualByComparingTo(new BigDecimal("92.00"));
    }

    @Test
    void shouldConvertZeroUsdToEur() {
        assertThat(converter.usdToEur(BigDecimal.ZERO)).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void shouldThrowForNegativeUsdToEur() {
        assertThatThrownBy(() -> converter.usdToEur(new BigDecimal("-10.00")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("USD amount cannot be negative");
    }

    @Test
    void shouldMaintainUsdAfterEurRoundTrip() {
        BigDecimal usd = new BigDecimal("100.00");
        BigDecimal eur = converter.usdToEur(usd);
        BigDecimal usdBack = converter.eurToUsd(eur);
        assertThat(usdBack).isEqualByComparingTo(usd);
    }

    // --- USD → SEK ---
    @Test
    void shouldConvertUsdToSek() {
        assertThat(converter.usdToSek(new BigDecimal("100.00"))).isEqualByComparingTo(new BigDecimal("1050.00"));
    }

    @Test
    void shouldConvert10UsdToSek() {
        assertThat(converter.usdToSek(new BigDecimal("10.00"))).isEqualByComparingTo(new BigDecimal("105.00"));
    }

    @Test
    void shouldMaintainSekAfterUsdRoundTrip() {
        BigDecimal sek = new BigDecimal("1050.00");
        BigDecimal usd = converter.sekToUsd(sek);
        BigDecimal sekBack = converter.usdToSek(usd);
        assertThat(sekBack).isEqualByComparingTo(sek);
    }

    // --- Money Addition ---
    @Test
    void shouldAddTwoPositiveAmounts() {
        assertThat(converter.addMoney(new BigDecimal("50.00"), new BigDecimal("25.50")))
                .isEqualByComparingTo(new BigDecimal("75.50"));
    }

    @Test
    void shouldAddZeroAmounts() {
        assertThat(converter.addMoney(new BigDecimal("100.00"), BigDecimal.ZERO))
                .isEqualByComparingTo(new BigDecimal("100.00"));
    }

    @Test
    void shouldThrowExceptionForNegativeAmount() {
        assertThatThrownBy(() -> converter.addMoney(new BigDecimal("-10.00"), BigDecimal.ONE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // --- Tax Calculations ---
    @Test
    void shouldCalculate25PercentTax() {
        assertThat(converter.calculateTax(new BigDecimal("100.00"), new BigDecimal("0.25")))
                .isEqualByComparingTo(new BigDecimal("25.00"));
    }

    @Test
    void shouldAddTaxToAmount() {
        assertThat(converter.addTaxAmount(new BigDecimal("100.00"), new BigDecimal("0.25")))
                .isEqualByComparingTo(new BigDecimal("125.00"));
    }

    @Test
    void shouldHandleZeroTaxRate() {
        assertThat(converter.addTaxAmount(new BigDecimal("100.00"), BigDecimal.ZERO))
                .isEqualByComparingTo(new BigDecimal("100.00"));
    }

    @Test
    void shouldThrowExceptionForNegativeTaxRate() {
        assertThatThrownBy(() -> converter.calculateTax(new BigDecimal("100.00"), new BigDecimal("-0.25")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
