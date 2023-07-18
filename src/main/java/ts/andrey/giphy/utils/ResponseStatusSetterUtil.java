package ts.andrey.giphy.utils;

import lombok.experimental.UtilityClass;
import ts.andrey.giphy.rest.dto.GiphyServiceResponse;
import ts.andrey.giphy.service.exception.ResponseErrorType;

@UtilityClass
public class ResponseStatusSetterUtil {

    public GiphyServiceResponse getErrorGiphyResponse(ResponseErrorType responseErrorType,
                                                      String baseCurrencyCode, String requestCurrencyCode) {
        return GiphyServiceResponse.builder()
                .errorCode(responseErrorType.getCode())
                .message(responseErrorType.getMessage())
                .baseCurrency(baseCurrencyCode)
                .requestCurrency(requestCurrencyCode)
                .build();
    }

    public GiphyServiceResponse setStatus(GiphyServiceResponse giphyServiceResponse, ResponseErrorType responseErrorType) {
        giphyServiceResponse.setErrorCode(responseErrorType.getCode());
        giphyServiceResponse.setMessage(responseErrorType.getMessage());
        return giphyServiceResponse;
    }

}
