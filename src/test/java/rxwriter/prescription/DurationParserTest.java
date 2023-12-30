package rxwriter.prescription;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DurationParserTest {

    @ParameterizedTest
    @CsvSource({"2 weeks, 14", "1 month, 30", "5 days, 5", "once, 1"})
    public void parseDurationWithValidUnitAndQuantity(String durationString, int expectedDays) {
        assertEquals(expectedDays, DurationParser.parseDays(durationString));
    }

}