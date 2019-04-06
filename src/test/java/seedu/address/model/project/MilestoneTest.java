package seedu.address.model.project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.util.PocketProjectDate;
import seedu.address.testutil.Assert;

public class MilestoneTest {

    //TODO: Add JUnit tests for constructor of milestones

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Milestone(null, null));
    }

    @Test
    public void constructor_invalidMilestone_throwsIllegalArgumentException() {
        PocketProjectDate invalidDate = null;
        Description invalidMilestone = null;
        Assert.assertThrows(IllegalArgumentException.class, () -> new Milestone(invalidMilestone, invalidDate));
    }

    @Test
    public void isValidMilestone() {

        // null fields
        Assert.assertThrows(NullPointerException.class, () -> Milestone.isValidMilestone(null));

    }
}
