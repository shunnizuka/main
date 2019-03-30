package seedu.address.model.project;


/**
 * Current status of the task in the Pocket Project.
 */

public class TaskStatus {

    public enum Status {
        ONGOING("ongoing"), COMPLETED("completed"), DELAYED("delayed");

        private String statusString;

        private Status(String s) {
            this.statusString = s;
        }

        public String getStatusString() {
            return this.statusString;
        }
    }

    public static final String MESSAGE_CONSTRAINTS =  "Task statuses can only be ongoing, complete or delayed";

    public final Status taskStatus;

    /**
     * Defaul constructor for TaskStatus.
     */
    public TaskStatus() {
        this.taskStatus = Status.ONGOING;
    }

    /**
     * Constructor for TaskStatus specifying status.
     */
    public TaskStatus(Status status) {
        this.taskStatus = status;
    }

    /**
     * Returns true if given status is valid for a task status
     */
    public static boolean isValidTaskStatus(Status test) {
        return true;
    }

    /**
     * Returns a clone of this TaskStatus object.
     */
    public TaskStatus clone() {
        return new TaskStatus(this.taskStatus);
    }

    @Override
    public String toString() {
        return this.taskStatus.getStatusString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
        || (other instanceof TaskStatus // instanceof handles nulls
        && taskStatus.equals(((TaskStatus) other).taskStatus)); // state check
    }

    @Override
    public int hashCode() {
        return taskStatus.hashCode();
    }
}
