package seedu.address.model.project;


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
        this.taskComplete = status;
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
