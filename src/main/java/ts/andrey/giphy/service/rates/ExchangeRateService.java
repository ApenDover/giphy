package ts.andrey.giphy.service.rates;

import com.neovisionaries.i18n.CurrencyCode;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ts.andrey.giphy.feign.CourseClient;
import ts.andrey.giphy.rest.dto.CourseResponse;
import ts.andrey.giphy.service.exception.ErrorType;
import ts.andrey.giphy.service.exception.GiphyException;

@Slf4j
@AllArgsConstructor
@Service
public class ExchangeRateService {

    private final CourseClient courseClient;

    public double giveActualExchangeRateByCurrencyCode(CurrencyCode currencyCode) throws FeignException {
        final CourseResponse rates;
        rates = courseClient.courseToday();
        if (rates.getRates().containsKey(currencyCode.toString())) {
            return rates.getRates().get(currencyCode.toString());
        } else {
            log.error(ErrorType.CURRENCY_NOT_FOUND.getMessage());
            throw new GiphyException(ErrorType.CURRENCY_NOT_FOUND);
        }
    }

    public double giveHistoryExchangeRateByCurrencyCode(CurrencyCode currencyCode, String date) throws FeignException {
        final CourseResponse rates;
        rates = courseClient.courseHistory(date);
        if (rates.getRates().containsKey(currencyCode.toString())) {
            return rates.getRates().get(currencyCode.toString());
        } else {
            log.error(ErrorType.CURRENCY_NOT_FOUND.getMessage());
            throw new GiphyException(ErrorType.CURRENCY_NOT_FOUND);
        }
    }

}


