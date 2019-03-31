package seedu.address.model.project;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Current status of the project task in the Pocket Project.
 */

public class ProjectTaskStatus {


    public static final String MESSAGE_CONSTRAINTS = "Task statuses can only be set to ongoing or complete";

    public final Boolean taskComplete;

    /**
     * Default constructor for ProjectTaskStatus.
     */
    public ProjectTaskStatus() {
        this.taskComplete = false;
    }

    /**
     * Constructor for TaskStatus specifying status.
     */
    public ProjectTaskStatus(Boolean status) {
        requireNonNull(status);
        checkArgument(isValidTaskStatus(status), MESSAGE_CONSTRAINTS);
        this.taskComplete = status;
    }

    /**
     * Returns true if given boolean is valid for task status
     */
    public static boolean isValidTaskStatus(Boolean status) {
        return status == true || status == false;
    }

    /**
     * Returns a clone of this TaskStatus object.
     */
    public ProjectTaskStatus clone() {
        return new ProjectTaskStatus(this.taskComplete);
    }

    @Override
    public String toString() {
        if (this.taskComplete) {
            return "Complete";
        }
        return "Ongoing";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectTaskStatus // instanceof handles nulls
                && taskComplete.equals(((ProjectTaskStatus) other).taskComplete)); // state check
    }

    @Override
    public int hashCode() {
        return taskComplete.hashCode();
    }
}
