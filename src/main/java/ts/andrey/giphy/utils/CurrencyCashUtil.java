package ts.andrey.giphy.utils;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ts.andrey.giphy.model.CurrencyDate;
import ts.andrey.giphy.model.CurrencyPair;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@UtilityClass
public class CurrencyCashUtil {

    private static final int CASH_SIZE = 100;

    private static final Map<CurrencyDate, CurrencyPair> CURRENCY_DATE_CASH = new HashMap<>();

    public CurrencyPair getActualFromCash(CurrencyCode currencyCode) {
        final var today = DateUtil.getTodayDate();
        final var currencyDate = new CurrencyDate(currencyCode, today);
        return CURRENCY_DATE_CASH.get(currencyDate);
    }

    public CurrencyPair getYesterdayFromCash(CurrencyCode currencyCode) {
        final var today = DateUtil.getPastDate(1);
        final var currencyDate = new CurrencyDate(currencyCode, today);
        return CURRENCY_DATE_CASH.get(currencyDate);
    }

    public void putToCash(CurrencyCode currencyCode, double rateToday, double rateYesterday) {
        if (CURRENCY_DATE_CASH.size() >= CASH_SIZE) {
            removeOldPair();
        }
        final var today = DateUtil.getTodayDate();
        final var currencyDate = new CurrencyDate(currencyCode, today);
        final var currencyPair = CurrencyPair.builder()
                .dateToday(today)
                .rateAmountToday(rateToday)
                .rateAmountYesterday(rateYesterday)
                .build();
        CURRENCY_DATE_CASH.put(currencyDate, currencyPair);
        log.info("{} add to cash for date: {}", currencyCode.toString(), today);
    }

    private void removeOldPair() {
        log.warn("Currency cash is full: {} elements. Clearing...", CURRENCY_DATE_CASH.size());
        final var currencyDateCashCopy = new HashMap<>(CURRENCY_DATE_CASH);

        for (CurrencyDate currencyDate : currencyDateCashCopy.keySet()) {
            if (LocalDate.parse(currencyDate.getDate())
                    .isBefore(LocalDate.parse(DateUtil.getPastDate(2)))) {
                CURRENCY_DATE_CASH.remove(currencyDate);
            }
        }
        log.warn("Currency cash size: {}", CURRENCY_DATE_CASH.size());
    }

}
