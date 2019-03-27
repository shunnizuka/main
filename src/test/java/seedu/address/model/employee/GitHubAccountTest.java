package seedu.address.model.employee;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class GitHubAccountTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new GitHubAccount(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new GitHubAccount(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> GitHubAccount.isValidAccount(null));

        // invalid addresses
        assertFalse(GitHubAccount.isValidAccount("")); // empty string
        assertFalse(GitHubAccount.isValidAccount(" ")); // spaces only

        // valid addresses
        assertTrue(GitHubAccount.isValidAccount("Blk 456, Den Road, #01-355"));
        assertTrue(GitHubAccount.isValidAccount("-")); // one character
        assertTrue(GitHubAccount.isValidAccount("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
