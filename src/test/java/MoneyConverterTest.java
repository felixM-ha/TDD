import ax.ha.it.MoneyConverter;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyConverterTest {

    private final MoneyConverter converter = new MoneyConverter();

    // --- USD to EUR ---
    @Nested
    class UsdToEurConversion {

        @Test
        void shouldConvert100UsdTo92Eur() {
            assertThat(converter.usdToEur(new BigDecimal("100.00")))
                    .isEqualByComparingTo(new BigDecimal("92.00"));
        }

        @Test
        void shouldConvert0UsdTo0Eur() {
            assertThat(converter.usdToEur(BigDecimal.ZERO))
                    .isEqualByComparingTo(BigDecimal.ZERO);
        }

        @Test
        void shouldThrowExceptionForNegativeUsd() {
            assertThatThrownBy(() -> converter.usdToEur(new BigDecimal("-10.00")))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("USD amount cannot be negative");
        }

        @Test
        void shouldMaintainUsdAfterRoundTrip() {
            BigDecimal usd = new BigDecimal("100.00");
            BigDecimal eur = converter.usdToEur(usd);
            BigDecimal usdBack = converter.eurToUsd(eur);
            assertThat(usdBack).isEqualByComparingTo(usd);
        }
    }

    // --- EUR to USD ---
    @Nested
    class EurToUsdConversion {

        @Test
        void shouldConvert92EurTo100Usd() {
            assertThat(converter.eurToUsd(new BigDecimal("92.00")))
                    .isEqualByComparingTo(new BigDecimal("100.00"));
        }

        @Test
        void shouldMaintainEurAfterRoundTrip() {
            BigDecimal eur = new BigDecimal("92.00");
            BigDecimal usd = converter.eurToUsd(eur);
            BigDecimal eurBack = converter.usdToEur(usd);
            assertThat(eurBack).isEqualByComparingTo(eur);
        }
    }

    // --- USD to SEK ---
    @Nested
    class UsdToSekConversion {

        @Test
        void shouldConvert100UsdTo1050Sek() {
            assertThat(converter.usdToSek(new BigDecimal("100.00")))
                    .isEqualByComparingTo(new BigDecimal("1050.00"));
        }

        @Test
        void shouldConvert10UsdTo105Sek() {
            assertThat(converter.usdToSek(new BigDecimal("10.00")))
                    .isEqualByComparingTo(new BigDecimal("105.00"));
        }

        @Test
        void shouldMaintainUsdAfterRoundTrip() {
            BigDecimal usd = new BigDecimal("100.00");
            BigDecimal sek = converter.usdToSek(usd);
            BigDecimal usdBack = converter.sekToUsd(sek);
            assertThat(usdBack).isEqualByComparingTo(usd);
        }
    }

    // --- SEK to USD ---
    @Nested
    class SekToUsdConversion {

        @Test
        void shouldConvert1050SekTo100Usd() {
            assertThat(converter.sekToUsd(new BigDecimal("1050.00")))
                    .isEqualByComparingTo(new BigDecimal("100.00"));
        }

        @Test
        void shouldMaintainSekAfterRoundTrip() {
            BigDecimal sek = new BigDecimal("1050.00");
            BigDecimal usd = converter.sekToUsd(sek);
            BigDecimal sekBack = converter.usdToSek(usd);
            assertThat(sekBack).isEqualByComparingTo(sek);
        }
    }

    // --- Money Addition ---
    @Nested
    class MoneyAddition {

        @Test
        void shouldAddTwoPositiveAmounts() {
            assertThat(converter.addMoney(new BigDecimal("50.00"), new BigDecimal("25.50")))
                    .isEqualByComparingTo(new BigDecimal("75.50"));
        }

        @Test
        void shouldAddZeroToAmount() {
            assertThat(converter.addMoney(new BigDecimal("100.00"), BigDecimal.ZERO))
                    .isEqualByComparingTo(new BigDecimal("100.00"));
        }

        @Test
        void shouldThrowExceptionForNegativeAmounts() {
            assertThatThrownBy(() -> converter.addMoney(new BigDecimal("-10.00"), BigDecimal.ONE))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // --- Tax Calculations ---
    @Nested
    class TaxCalculations {

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
}
