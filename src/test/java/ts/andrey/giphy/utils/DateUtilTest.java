package ts.andrey.giphy.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilTest {

    private transient DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    void getPastDate() {
        //GIVEN
        final var dateToday = LocalDate.now().minusDays(1).format(formatter);

        //WHEN
        final var actual = DateUtil.getPastDate(1);

        //THEN
        assertEquals(dateToday, actual);

    }

    @Test
    void getTodayDate() {
        //GIVEN
        final var dateToday = LocalDate.now().format(formatter);

        //WHEN
        final var actual = DateUtil.getTodayDate();

        //THEN
        assertEquals(dateToday, actual);

    }

}
