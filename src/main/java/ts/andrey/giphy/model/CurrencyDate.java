package ts.andrey.giphy.model;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyDate {

    private CurrencyCode currencyCode;

    private String date;

}
