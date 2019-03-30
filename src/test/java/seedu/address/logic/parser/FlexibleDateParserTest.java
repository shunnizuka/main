package seedu.address.logic.parser;

import seedu.address.model.project.FlexibleDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.TypicalFlexibleDateInputs.THIS_MONTH_END;
import static seedu.address.testutil.TypicalFlexibleDateInputs.THIS_MONTH_MID;
import static seedu.address.testutil.TypicalFlexibleDateInputs.THIS_MONTH_START;
import static seedu.address.testutil.TypicalFlexibleDateInputs.THIS_WEEK_MONDAY;
import static seedu.address.testutil.TypicalFlexibleDateInputs.THIS_WEEK_SUNDAY;
import static seedu.address.testutil.TypicalFlexibleDateInputs.THIS_WEEK_WEDNESDAY;
import static seedu.address.testutil.TypicalFlexibleDateInputs.TODAY;
import static seedu.address.testutil.TypicalFlexibleDateInputs.TOMORROW;
import static seedu.address.testutil.TypicalFlexibleDateInputs.YESTERDAY;

public class FlexibleDateParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final FlexibleDateParser parser = new FlexibleDateParser();

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    public void parseFlexibleDate_today() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(TODAY);

        assertEquals(currentDate.currentDate(), userInput);
    }

    @Test
    public void parseFlexibleDate_tomorrow() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(TOMORROW);

        assertEquals(currentDate.dateNumDaysLater(1), userInput);
    }

    @Test
    public void parseFlexibleDate_yesterday() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(YESTERDAY);

        assertEquals(currentDate.dateNumDaysBefore(1), userInput);
    }

    @Test
    public void parseFlexibleDate_thisWeekStart() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(THIS_WEEK_MONDAY);

        assertEquals(currentDate.thisWeekDate(1), userInput);
    }

    @Test
    public void parseFlexibleDate_thisWeekMid() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(THIS_WEEK_WEDNESDAY);

        assertEquals(currentDate.thisWeekDate(3), userInput);
    }

    @Test
    public void parseFlexibleDate_thisWeekEnd() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(THIS_WEEK_SUNDAY);

        assertEquals(currentDate.thisWeekDate(7), userInput);
    }

    @Test
    public void parseFlexibleDate_thisMonthStart() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(THIS_MONTH_START);

        assertEquals(currentDate.thisMonthDate(1), userInput);
    }

    @Test
    public void parseFlexibleDate_thisMonthMid() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(THIS_MONTH_MID);

        assertEquals(currentDate.thisMonthDate(15), userInput);
    }

    @Test
    public void parseFlexibleDate_thisMonthEnd() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(THIS_MONTH_END);

        assertEquals(currentDate.thisMonthDate(30), userInput);
    }



}
