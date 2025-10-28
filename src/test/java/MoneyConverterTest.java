import ax.ha.it.MoneyConverter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyConverterTest {

    private final MoneyConverter converter = new MoneyConverter();

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

    @Test
    void shouldAddTwoPositiveAmounts() {
        BigDecimal result = converter.addMoney(new BigDecimal("50.00"), new BigDecimal("25.50"));
        assertThat(result).isEqualByComparingTo(new BigDecimal("75.50"));
    }

    @Test
    void shouldAddZeroAmounts() {
        BigDecimal result = converter.addMoney(new BigDecimal("100.00"), BigDecimal.ZERO);
        assertThat(result).isEqualByComparingTo(new BigDecimal("100.00"));
    }

    @Test
    void shouldThrowExceptionForNegativeAmount() {
        assertThatThrownBy(() -> converter.addMoney(new BigDecimal("-10.00"), new BigDecimal("5.00")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Amounts cannot be negative");
    }

    @Test
    void shouldCalculate25PercentTax() {
        MoneyConverter converter = new MoneyConverter();
        BigDecimal amount = new BigDecimal("100.00");
        BigDecimal taxRate = new BigDecimal("0.25");

        BigDecimal tax = converter.calculateTax(amount, taxRate);

        assertThat(tax).isEqualByComparingTo(new BigDecimal("25.00"));
    }

    @Test
    void shouldAddTaxToAmount() {
        MoneyConverter converter = new MoneyConverter();
        BigDecimal amount = new BigDecimal("100.00");
        BigDecimal taxRate = new BigDecimal("0.25");

        BigDecimal total = converter.addTaxAmount(amount, taxRate);

        assertThat(total).isEqualByComparingTo(new BigDecimal("125.00"));
    }

    @Test
    void shouldHandleZeroTaxRate() {
        MoneyConverter converter = new MoneyConverter();
        BigDecimal amount = new BigDecimal("100.00");
        BigDecimal taxRate = BigDecimal.ZERO;

        BigDecimal total = converter.addTaxAmount(amount, taxRate);

        assertThat(total).isEqualByComparingTo(new BigDecimal("100.00"));
    }

    @Test
    void shouldThrowExceptionForNegativeTaxRate() {
        MoneyConverter converter = new MoneyConverter();

        assertThatThrownBy(() ->
                converter.calculateTax(new BigDecimal("100.00"), new BigDecimal("-0.25"))
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Tax rate must not be negative");
    }

}
