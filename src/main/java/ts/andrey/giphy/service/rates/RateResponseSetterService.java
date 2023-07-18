package ts.andrey.giphy.service.rates;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ts.andrey.giphy.rest.dto.GiphyServiceResponse;
import ts.andrey.giphy.service.exception.ResponseErrorType;
import ts.andrey.giphy.utils.ResponseStatusSetterUtil;
import ts.andrey.giphy.utils.CurrencyCashUtil;
import ts.andrey.giphy.utils.DateUtil;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class RateResponseSetterService {

    private final ExchangeRateService exchangeRateService;

    public GiphyServiceResponse setRate(GiphyServiceResponse giphyServiceResponse, CurrencyCode currencyCode) {

        final double todayRateValue;
        double yesterdayRateValue;

        try {
            todayRateValue = exchangeRateService.giveActualExchangeRateByCurrencyCode(currencyCode);
            yesterdayRateValue = exchangeRateService.giveHistoryExchangeRateByCurrencyCode(currencyCode, DateUtil.getPastDate(1));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseStatusSetterUtil.setStatus(giphyServiceResponse, ResponseErrorType.ERROR);
        }

        if (todayRateValue == yesterdayRateValue) {
            log.warn("Actual rate for this day is not available now");
            if (Objects.isNull(CurrencyCashUtil.getYesterdayFromCash(currencyCode))) {
                log.warn("Actual rate for this day is not available in cash too");
                yesterdayRateValue = exchangeRateService.giveHistoryExchangeRateByCurrencyCode(currencyCode, DateUtil.getPastDate(2));
            } else {
                yesterdayRateValue = CurrencyCashUtil.getYesterdayFromCash(currencyCode).getRateAmountYesterday();
            }
        } else {
            CurrencyCashUtil.putToCash(currencyCode, todayRateValue, yesterdayRateValue);
        }

        giphyServiceResponse.setRateAmountToday(todayRateValue);
        giphyServiceResponse.setRateAmountYesterday(yesterdayRateValue);

        return giphyServiceResponse;
    }

}
