package ts.andrey.giphy.service;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ts.andrey.giphy.rest.dto.GiphyServiceResponse;
import ts.andrey.giphy.service.exception.ResponseErrorType;
import ts.andrey.giphy.service.giphy.GiphyResponseSetterService;
import ts.andrey.giphy.service.rates.RateResponseSetterService;
import ts.andrey.giphy.utils.ResponseStatusSetterUtil;
import ts.andrey.giphy.utils.CurrencyCashUtil;
import ts.andrey.giphy.configuration.Property;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActualGiphyByExchangeRateService {

    private final Property property;

    private final RateResponseSetterService rateResponseSetterService;

    private final GiphyResponseSetterService giphyResponseSetterService;

    public GiphyServiceResponse getResponse(int currencyNumber) {

        final var currencyCode = CurrencyCode.getByCode(currencyNumber);

        if (Objects.isNull(currencyCode)) {
            log.error("currency {} not found", currencyNumber);
            return ResponseStatusSetterUtil.getErrorGiphyResponse(ResponseErrorType.ERROR,
                    property.getBaseCurrency(), String.valueOf(currencyNumber));
        }

        final var giphyServiceResponse = GiphyServiceResponse.builder()
                .requestCurrency(currencyCode.toString())
                .baseCurrency(property.getBaseCurrency())
                .cash(false)
                .build();

        final var cashPair = CurrencyCashUtil.getActualFromCash(currencyCode);

        if (Objects.isNull(cashPair)) {
            rateResponseSetterService.setRate(giphyServiceResponse, currencyCode);
        } else {
            giphyServiceResponse.setRateAmountToday(cashPair.getRateAmountToday());
            giphyServiceResponse.setRateAmountYesterday(cashPair.getRateAmountYesterday());
            log.info("get currency from cash");
        }

        giphyResponseSetterService.setGiphy(giphyServiceResponse);

        log.info("currency pair: {}; rate today: {}; rate yesterday: {}",
                property.getBaseCurrency() + " - " + currencyCode, giphyServiceResponse.getRateAmountToday(),
                giphyServiceResponse.getRateAmountYesterday());

        return ResponseStatusSetterUtil.setStatus(giphyServiceResponse, ResponseErrorType.SUCCESS);

    }

}
