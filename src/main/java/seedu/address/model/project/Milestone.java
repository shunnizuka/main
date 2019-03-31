package seedu.address.model.project;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.text.DateFormat;
import java.text.ParseException;

import javafx.collections.ObservableList;

/**
 * Milestone achieved in the project timeline.
 */

public class Milestone {

    public static final String DATE_VALIDATION_REGEX =
            "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
    public static final String MESSAGE_CONSTRAINTS = "The milestone info must not be empty or consisting of only spaces"
            + " and the date given must be in DD/MM/YYYY format";
    public static final String MESSAGE_INVALID_STRING = "The milestone info must not be empty or consisting "
        + "of only spaces";
    public static final String MESSAGE_INVALID_DATE = "The date given must be in DD/MM/YYYY format";

    public final String milestone;
    public final String date;
    public final UniqueProjectTaskList projectTasks;


    public Milestone(String milestone, String date) {
        this(milestone, date, new UniqueProjectTaskList());
    }

    public Milestone(String milestone, String date, UniqueProjectTaskList projectTasks) {
        requireAllNonNull(milestone, date, projectTasks);
        checkArgument(isValidMilestoneString(milestone), MESSAGE_INVALID_STRING);
        checkArgument(isValidMilestoneDate(date), MESSAGE_INVALID_DATE);

        this.milestone = milestone;
        this.date = date;
        this.projectTasks = projectTasks;
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

    /**
     * Adds the given project task to this milestone.
     */
    public void addTask(ProjectTask task) {
        this.projectTasks.add(task);
    }

    /**
     * Returns a clone of this Milestone object.
     */
    public Milestone clone() {

        return new Milestone(this.milestone, this.date, this.projectTasks.clone());
    }

    public String getMilestone() {
        return milestone;
    }

    public String getDate() {
        return date;
    }

    public ObservableList<ProjectTask> getProjectTaskList() {
        return this.projectTasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Milestone // instanceof handles nulls
                && milestone.equals(((Milestone) other).milestone)
                && date.equals(((Milestone) other).date)
                && projectTasks.equals(((Milestone) other).projectTasks)); // state check
    }
    @Override
    public String toString() {
        return this.milestone + " " + this.date;
    }

}


