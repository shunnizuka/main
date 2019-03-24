package seedu.address.testutil;

import seedu.address.model.project.Milestone;

/**
 * A utility class containing a list of {@code Milestones} objects to be used in tests.
 */
public class TypicalMilestones {
    public static final Milestone TYPICAL_MILESTONE_START = new Milestone("The project starts",
            "11/11/2011");

    public static final Milestone TYPICAL_MILESTONE_END = new Milestone("The project completes",
            "12/12/2012");

    public static final Milestone TYPICAL_MILESTONE_COMPLETED_UG = new Milestone("The UG is completed",
            "10/11/2012");
}
