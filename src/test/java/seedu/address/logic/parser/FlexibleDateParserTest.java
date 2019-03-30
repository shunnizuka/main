package seedu.address.logic.parser;

import seedu.address.model.project.FlexibleDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class FlexibleDateParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final FlexibleDateParser parser = new FlexibleDateParser();

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    public void parseFlexibleDate_today() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate("today");
        assertEquals(currentDate.currentDate(), userInput);
    }

    @Test
    public void parseFlexibleDate_tomorrow() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate("tomorrow");
        assertEquals(currentDate.dateNumDaysLater(1), userInput);
    }

    @Test
    public void parseFlexibleDate_yesterday() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate("yesterday");
        assertEquals(currentDate.dateNumDaysBefore(1), userInput);
    }

}
