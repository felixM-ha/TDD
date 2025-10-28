package ax.ha.it;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyConverter {

    private static final BigDecimal USD_TO_EUR = new BigDecimal("0.92");
    private static final BigDecimal EUR_TO_USD = BigDecimal.ONE.divide(USD_TO_EUR, 6, RoundingMode.HALF_UP);

    private static final BigDecimal USD_TO_SEK = new BigDecimal("10.50");
    private static final BigDecimal SEK_TO_USD = BigDecimal.ONE.divide(USD_TO_SEK, 6, RoundingMode.HALF_UP);

    public BigDecimal usdToEur(BigDecimal usd) {
        return usd.multiply(USD_TO_EUR).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal eurToUsd(BigDecimal eur) {
        return eur.multiply(EUR_TO_USD).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal usdToSek(BigDecimal usd) {
        return usd.multiply(USD_TO_SEK).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal sekToUsd(BigDecimal sek) {
        return sek.multiply(SEK_TO_USD).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal addMoney(BigDecimal amount1, BigDecimal amount2) {
        if (amount1 == null || amount2 == null) {
            throw new IllegalArgumentException("Amounts cannot be null");
        }
        if (amount1.compareTo(BigDecimal.ZERO) < 0 || amount2.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amounts cannot be negative");
        }

        return amount1.add(amount2).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateTax(BigDecimal amount, BigDecimal taxRate) {
        if (amount == null || taxRate == null) {
            throw new IllegalArgumentException("Amount and tax rate must not be null");
        }
        if (taxRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Tax rate must not be negative");
        }

        return amount.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal addTaxAmount(BigDecimal amount, BigDecimal taxRate) {
        if (amount == null || taxRate == null) {
            throw new IllegalArgumentException("Amount and tax rate must not be null");
        }
        if (taxRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Tax rate must not be negative");
        }

        BigDecimal tax = calculateTax(amount, taxRate);
        return amount.add(tax).setScale(2, RoundingMode.HALF_UP);
    }


}
