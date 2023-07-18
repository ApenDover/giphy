package ts.andrey.giphy.utils;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
@Slf4j
public class DateUtil {

    @Getter
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String getPastDate(int dayBefore) {
        final var yesterday = LocalDate.now().minusDays(dayBefore);
        return yesterday.format(FORMATTER);
    }

    public String getTodayDate() {
        final var today = LocalDate.now();
        return today.format(FORMATTER);
    }

}
