package ts.andrey.giphy.service.rates;

import com.neovisionaries.i18n.CurrencyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ts.andrey.giphy.feign.CourseClient;
import ts.andrey.giphy.rest.dto.CourseResponse;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceTest {

    @Mock
    private transient CourseClient courseClient;

    private transient ExchangeRateService service;

    private final transient HashMap<String, Double> rate = new HashMap<>();

    private final transient CourseResponse courseResponse = new CourseResponse();

    @BeforeEach
    void setup() {
        service = new ExchangeRateService(courseClient);
        rate.put("RUB", 2.2);
        courseResponse.setRates(rate);
        courseResponse.setBase("USD");
    }

    @Test
    void giveActualExchangeRateByCurrencyCode() {
        //GIVEN
        when(courseClient.courseToday()).thenReturn(courseResponse);

        //WHEN
        var actual = service.giveActualExchangeRateByCurrencyCode(CurrencyCode.RUB);

        //THEN
        assertEquals(2.2, actual);
    }

    @Test
    void giveHistoryExchangeRateByCurrencyCode() {
        //GIVEN
        when(courseClient.courseHistory(any())).thenReturn(courseResponse);

        //WHEN
        var actual = service.giveHistoryExchangeRateByCurrencyCode(CurrencyCode.RUB, "2023-07-16");

        //THEN
        assertEquals(2.2, actual);
    }

}
