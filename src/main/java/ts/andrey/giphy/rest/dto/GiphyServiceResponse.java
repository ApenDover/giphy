package ts.andrey.giphy.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GiphyServiceResponse {

    private int errorCode;

    private String message;

    private boolean cash;

    private String giphyUrl;

    private String giphyMood;

    private String baseCurrency;

    private String requestCurrency;

    private double rateAmountToday;

    private double rateAmountYesterday;

}
