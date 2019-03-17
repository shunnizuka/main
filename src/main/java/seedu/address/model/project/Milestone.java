package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;

/**
 * Milestone achieved in the project timeline.
 */

public class Milestone {

    public static final String DATE_VALIDATION_REGEX =
            "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
    public static final String MESSAGE_CONSTRAINTS = "The milestone info must not be empty or consisting on ony spaces"
            + " and the date given must be in DD/MM/YYYY format";
    public final String milestone;
    public final String date;



    public Milestone(String milestone, String date) {
        requireNonNull(milestone);
        checkArgument(isValidMilestoneString(milestone), MESSAGE_CONSTRAINTS);
        requireNonNull(date);
        checkArgument(isValidMilestoneDate(date), MESSAGE_CONSTRAINTS);
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
        DateFormat format = Project.DATE_FORMAT;
        format.setLenient(false);
        try {
            format.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return date.matches(DATE_VALIDATION_REGEX);
    }

    /**
     * Returns true if given string is valid for a milestone string
     */
    public static boolean isValidMilestoneString(String info) {
        return !info.trim().isEmpty();
    }

}


