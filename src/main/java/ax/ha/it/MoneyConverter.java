package ax.ha.it;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyConverter {

    private static final BigDecimal USD_TO_EUR = new BigDecimal("0.92");

    public BigDecimal usdToEur(BigDecimal usd) {
        return usd.multiply(USD_TO_EUR).setScale(2, RoundingMode.HALF_UP);
    }
}
