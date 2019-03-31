package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Current status of the project task in the Pocket Project.
 */

public class ProjectTaskStatus {


    public static final String MESSAGE_CONSTRAINTS = "Task statuses can only be set to ongoing or complete";
    public static final String STATUS_COMPLETE = "complete";
    public static final String STATUS_ONGOING = "ongoing";

    public final String taskStatus;

    /**
     * Default constructor for ProjectTaskStatus.
     */
    public ProjectTaskStatus() {
        this.taskStatus = STATUS_ONGOING;
    }

    /**
     * Constructor for TaskStatus specifying status.
     */
    public ProjectTaskStatus(String status) {
        requireNonNull(status);
        checkArgument(isValidTaskStatus(status), MESSAGE_CONSTRAINTS);
        this.taskStatus = status;
    }

    public String getTaskStatusString() {
        return this.taskStatus;
    }

    /**
     * Returns true if given string is valid for task status
     */
    public static boolean isValidTaskStatus(String status) {
        return STATUS_COMPLETE.equalsIgnoreCase(status) || STATUS_ONGOING.equalsIgnoreCase(status);
    }

    /**
     * Returns a clone of this TaskStatus object.
     */
    public ProjectTaskStatus clone() {
        return new ProjectTaskStatus(this.taskStatus);
    }

    @Override
    public String toString() {
        return this.taskStatus;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectTaskStatus // instanceof handles nulls
                && taskStatus.equals(((ProjectTaskStatus) other).taskStatus)); // state check
    }

    @Override
    public int hashCode() {
        return taskStatus.hashCode();
    }
}
