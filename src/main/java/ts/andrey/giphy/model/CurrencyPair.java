package ts.andrey.giphy.model;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyPair {

    private CurrencyCode currencyCode;

    private double rateAmountToday;

    private double rateAmountYesterday;

    private String dateToday;

}
