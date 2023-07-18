package ts.andrey.giphy.service.rates;

import com.neovisionaries.i18n.CurrencyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ts.andrey.giphy.rest.dto.GiphyServiceResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RateResponseSetterServiceTest {

    private transient RateResponseSetterService service;

    @Mock
    private transient ExchangeRateService exchangeRateService;

    @BeforeEach
    void setup() {
        service = new RateResponseSetterService(exchangeRateService);
    }

    @Test
    void setRate() {
        //GIVEN
        when(exchangeRateService.giveActualExchangeRateByCurrencyCode(any())).thenReturn(2.5);
        when(exchangeRateService.giveHistoryExchangeRateByCurrencyCode(any(), any())).thenReturn(3.5);
        final var giphyServiceResponse = new GiphyServiceResponse();

        //WHEN
        service.setRate(giphyServiceResponse, CurrencyCode.RUB);

        //THEN
        assertEquals(2.5, giphyServiceResponse.getRateAmountToday());
        assertEquals(3.5, giphyServiceResponse.getRateAmountYesterday());
    }

}
