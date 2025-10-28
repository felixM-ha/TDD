package ax.ha.it;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyConverter {

    private static final BigDecimal USD_TO_EUR = new BigDecimal("0.92");
    private static final BigDecimal EUR_TO_USD = BigDecimal.ONE.divide(USD_TO_EUR, 6, RoundingMode.HALF_UP);

    private static final BigDecimal USD_TO_SEK = new BigDecimal("10.50");


    public BigDecimal usdToEur(BigDecimal usd) {
        return usd.multiply(USD_TO_EUR).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal eurToUsd(BigDecimal eur) {
        return eur.multiply(EUR_TO_USD).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal usdToSek(BigDecimal usd) {
        return usd.multiply(USD_TO_SEK).setScale(2, RoundingMode.HALF_UP);
    }

}
