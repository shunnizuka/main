package seedu.address.model.project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ProjectDate(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ProjectDate(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null date
        Assert.assertThrows(NullPointerException.class, () -> ProjectDate.isValidDate(null));

        // blank email
        assertFalse(ProjectDate.isValidDate("")); // empty string
        assertFalse(ProjectDate.isValidDate(" ")); // spaces only

        // missing parts
        assertFalse(ProjectDate.isValidDate("12")); // missing both the month/date and the year value
        assertFalse(ProjectDate.isValidDate("12/12")); // missing the year value
        assertFalse(ProjectDate.isValidDate("12/2019")); // missing the month/date value

        // invalid parts
        assertFalse(ProjectDate.isValidDate("12//12/2019")); // wrong input pattern
        assertFalse(ProjectDate.isValidDate("12/12/12/2019")); // too many fields
        assertFalse(ProjectDate.isValidDate("111/11/2019")); // too many digits in the date field
        assertFalse(ProjectDate.isValidDate("11/111/2019")); // too many digits in the month field
        assertFalse(ProjectDate.isValidDate("11/11/11111")); // too many digits in the date field
        assertFalse(ProjectDate.isValidDate(" 11/11/2019")); // leading space
        assertFalse(ProjectDate.isValidDate("11/11/2019 ")); // trailing space
        assertFalse(ProjectDate.isValidDate("aa/11/2019")); // alphabetical date
        assertFalse(ProjectDate.isValidDate("11/aa/2019")); // alphabetical month
        assertFalse(ProjectDate.isValidDate("11/11/aaaa")); // alphabetical year
        assertFalse(ProjectDate.isValidDate("44/11/2019")); // invalid date
        assertFalse(ProjectDate.isValidDate("11/44/aaaa")); // invalid month
        assertFalse(ProjectDate.isValidDate("11/11/1800")); // invalid year too old

        // valid deadlines
        assertTrue(ProjectDate.isValidDate("11/11/2019"));
        assertTrue(ProjectDate.isValidDate("1/1/2000")); // minimal
    }
}
