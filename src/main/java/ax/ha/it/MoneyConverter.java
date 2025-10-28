package ax.ha.it;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyConverter {

    private static final BigDecimal USD_TO_EUR = new BigDecimal("0.92");
    private static final BigDecimal EUR_TO_USD = BigDecimal.ONE.divide(USD_TO_EUR, 6, RoundingMode.HALF_UP);

    private static final BigDecimal USD_TO_SEK = new BigDecimal("10.50");
    private static final BigDecimal SEK_TO_USD = BigDecimal.ONE.divide(USD_TO_SEK, 6, RoundingMode.HALF_UP);

    // --- USD ↔ EUR ---
    public BigDecimal usdToEur(BigDecimal usd) {
        validateNonNegative(usd, "USD amount");
        return usd.multiply(USD_TO_EUR).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal eurToUsd(BigDecimal eur) {
        validateNonNegative(eur, "EUR amount");
        return eur.multiply(EUR_TO_USD).setScale(2, RoundingMode.HALF_UP);
    }

    // --- USD ↔ SEK ---
    public BigDecimal usdToSek(BigDecimal usd) {
        validateNonNegative(usd, "USD amount");
        return usd.multiply(USD_TO_SEK).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal sekToUsd(BigDecimal sek) {
        validateNonNegative(sek, "SEK amount");
        return sek.multiply(SEK_TO_USD).setScale(2, RoundingMode.HALF_UP);
    }

    // --- Money Addition ---
    public BigDecimal addMoney(BigDecimal amount1, BigDecimal amount2) {
        if (amount1 == null || amount2 == null) throw new IllegalArgumentException("Amounts cannot be null");
        validateNonNegative(amount1, "First amount");
        validateNonNegative(amount2, "Second amount");
        return amount1.add(amount2).setScale(2, RoundingMode.HALF_UP);
    }

    // --- Tax Calculations ---
    public BigDecimal calculateTax(BigDecimal amount, BigDecimal taxRate) {
        if (amount == null || taxRate == null) throw new IllegalArgumentException("Amount and tax rate cannot be null");
        validateNonNegative(taxRate, "Tax rate");
        return amount.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal addTaxAmount(BigDecimal amount, BigDecimal taxRate) {
        return amount.add(calculateTax(amount, taxRate)).setScale(2, RoundingMode.HALF_UP);
    }

    // --- Helper ---
    private void validateNonNegative(BigDecimal value, String name) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(name + " cannot be negative");
        }
    }
}
