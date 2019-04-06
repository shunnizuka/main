package seedu.address.model.project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;



public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidClient_throwsIllegalArgumentException() {
        String invalidDescription = ""; //empty string
        Assert.assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));

        String invaliddDescription = " "; //whitespace
        Assert.assertThrows(IllegalArgumentException.class, () -> new Description(invaliddDescription));

    }

    @Test
    public void isValidDescription() {
        // null client
        Assert.assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid names
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        //valid names
        assertTrue(Description.isValidDescription("a")); //single letter
        assertTrue(Description.isValidDescription("apple")); //word
        assertTrue(Description.isValidDescription("apple pie")); //words
        assertTrue(Description.isValidDescription("apple 9 completed")); //alphanumeric
        assertTrue(Description.isValidDescription("apple & oranges")); //symbols
    }
}
