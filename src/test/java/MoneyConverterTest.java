import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class MoneyConverterTest {

    @Test
    void converter() {
        BigDecimal usd = new BigDecimal("100.00");
        BigDecimal result = converter.usdToEur(usd);
        assert (result).compareTo("92.00");
    }

}
