package seedu.address.model.project;

/**
 * Milestone achieved in the project timeline.
 */

public class Milestone {

    public static final String DATE_VALIDATION_REGEX =
            "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
    public static final String MILESTONE_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String MESSAGE_CONSTRAINTS =
            "Milestones should be alphanumeric and not start with whitespace."
            + "The date given must be in DD/MM/YYYY format";
    public final String milestone;
    public final String date;


    public Milestone(String milestone, String date) {
        this.milestone = milestone;
        this.date = date;
    }

    /**
     * Returns true if given strings are valid fields for a milestone.
     */
    public static boolean isValidMilestone(String info, String date) {
        return Milestone.isValidMilestoneDate(date)
                && Milestone.isValidMilestoneString(info);
    }

    /**
     * Returns true if given string is valid for a milestone date
     */
    public static boolean isValidMilestoneDate(String date) {
        return date.matches(DATE_VALIDATION_REGEX);
    }

    /**
     * Returns true if given string is valid for a milestone string
     */
    public static boolean isValidMilestoneString(String info) {
        return info.matches(MILESTONE_VALIDATION_REGEX);
    }
}
