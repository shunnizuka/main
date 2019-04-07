package seedu.address.testutil;

import seedu.address.model.project.Description;
import seedu.address.model.project.Milestone;
import seedu.address.model.util.PocketProjectDate;

/**
 * A utility class containing a list of {@code Milestones} objects to be used in tests.
 */
public class TypicalMilestones {
    public static final Milestone TYPICAL_MILESTONE_START = new Milestone(new Description("The project starts"),
            new PocketProjectDate("11/11/2011"));

    public static final Milestone TYPICAL_MILESTONE_END = new Milestone(new Description("The project completes"),
            new PocketProjectDate("12/12/2012"));

    public static final Milestone TYPICAL_MILESTONE_COMPLETED_UG = new Milestone(new Description("The UG is completed"),
            new PocketProjectDate("10/11/2012"));

    public static final Milestone TYPICAL_MILESTONE_GUI_V2 = new Milestone(new Description("GUI is in version 2"),
            new PocketProjectDate("08/03/2012"));
}
