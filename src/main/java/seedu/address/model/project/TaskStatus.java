package seedu.address.model.project;


/**
 * Current status of the task in the Pocket Project.
 */

public class TaskStatus {


    public static final String MESSAGE_CONSTRAINTS = "Task statuses can only be set to ongoing or complete";

    public final Boolean taskComplete;

    /**
     * Defaul constructor for TaskStatus.
     */
    public TaskStatus() {
        this.taskComplete = false;
    }

    /**
     * Constructor for TaskStatus specifying status.
     */
    public TaskStatus(Boolean status) {
        this.taskComplete = status;
    }

    /**
     * Returns a clone of this TaskStatus object.
     */
    public TaskStatus clone() {
        return new TaskStatus(this.taskComplete);
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
                || (other instanceof TaskStatus // instanceof handles nulls
                && taskComplete.equals(((TaskStatus) other).taskComplete)); // state check
    }

    @Override
    public int hashCode() {
        return taskComplete.hashCode();
    }
}
