package seedu.address.model.project;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.util.PocketProjectDate;
import seedu.address.testutil.Assert;

public class MilestoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Milestone(null, null));
    }

    @Test
    public void constructor_invalidMilestone_throwsIllegalArgumentException() {
        PocketProjectDate invalidDate = null;
        Description invalidMilestone = null;
        Assert.assertThrows(NullPointerException.class, () -> new Milestone(invalidMilestone, invalidDate));
    }

    @Test
    public void isValidMilestone() {

        // null fields
        Assert.assertThrows(NullPointerException.class, () -> Milestone.isValidMilestone(null));

        //invalid description string
        Assert.assertThrows(IllegalArgumentException.class, () -> new Milestone(new Description(""),
            new PocketProjectDate("23/04/2020")));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Milestone(new Description(" "),
            new PocketProjectDate("23/04/2020")));

        //too many numbers -> out of range
        Assert.assertThrows(IllegalArgumentException.class, () -> new Milestone(new Description("a"),
            new PocketProjectDate("233/04/2020")));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Milestone(new Description("a"),
            new PocketProjectDate("23/133/2020")));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Milestone(new Description("a"),
            new PocketProjectDate("23/04/22020")));

        //too many parameters
        Assert.assertThrows(IllegalArgumentException.class, () -> new Milestone(new Description("a"),
            new PocketProjectDate("23/04/2020/23")));

        //valid fields without padding
        assertTrue(Milestone.isValidMilestone(new Milestone(new Description("america"),
            new PocketProjectDate("23/11/2019"))));
        assertTrue(Milestone.isValidMilestone(new Milestone(new Description("america"),
            new PocketProjectDate("04/03/2019"))));

        //valid fields with padding
        assertTrue(Milestone.isValidMilestone(new Milestone(new Description("america"),
            new PocketProjectDate("4/3/2019"))));

    }


}
