package seedu.address.model.project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.util.PocketProjectDate;
import seedu.address.testutil.Assert;

public class DeadlineTest {

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new PocketProjectDate(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null date
        Assert.assertThrows(NullPointerException.class, () -> PocketProjectDate.isValidDate(null));

        // blank entry
        assertFalse(PocketProjectDate.isValidDate("")); // empty string
        assertFalse(PocketProjectDate.isValidDate(" ")); // spaces only

        // missing parts
        assertFalse(PocketProjectDate.isValidDate("12")); // missing both the month/date and the year value
        assertFalse(PocketProjectDate.isValidDate("12/12")); // missing the year value
        assertFalse(PocketProjectDate.isValidDate("12/2019")); // missing the month/date value

        // invalid parts
        assertFalse(PocketProjectDate.isValidDate("12//12/2019")); // wrong input pattern
        assertFalse(PocketProjectDate.isValidDate("12/12/12/2019")); // too many fields
        assertFalse(PocketProjectDate.isValidDate("111/11/2019")); // too many digits in the date field
        assertFalse(PocketProjectDate.isValidDate("11/111/2019")); // too many digits in the month field
        assertFalse(PocketProjectDate.isValidDate("11/11/11111")); // too many digits in the date field
        assertFalse(PocketProjectDate.isValidDate(" 11/11/2019")); // leading space
        assertFalse(PocketProjectDate.isValidDate("11/11/2019 ")); // trailing space
        assertFalse(PocketProjectDate.isValidDate("aa/11/2019")); // alphabetical date
        assertFalse(PocketProjectDate.isValidDate("11/aa/2019")); // alphabetical month
        assertFalse(PocketProjectDate.isValidDate("11/11/aaaa")); // alphabetical year
        assertFalse(PocketProjectDate.isValidDate("44/11/2019")); // invalid date
        assertFalse(PocketProjectDate.isValidDate("11/44/aaaa")); // invalid month
        assertFalse(PocketProjectDate.isValidDate("11/11/1800")); // invalid year too old

        // valid deadlines
        assertTrue(PocketProjectDate.isValidDate("11/11/2019"));
        assertTrue(PocketProjectDate.isValidDate("1/1/2000")); // minimal
    }
}
