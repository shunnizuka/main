package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

/**
 * Name of the task in the Pocket Project.
 */

public class TaskName {

    public static final String MESSAGE_CONSTRAINTS =  "Task names should not be empty or consisting of only spaces.";

    public final String taskName;

    /**
     * Constructor for TaskName.
     */
    public TaskName(String taskName) {
        requireNonNull(taskName);
        this.taskName = taskName;
    }

    /**
     * Returns true if given string is valid for a task name string
     */
    public static boolean isValidTaskNameString(String info) {
        return !info.trim().isEmpty();
    }

    /**
     * Returns a clone of this TaskName object.
     */
    public TaskName clone() {
        return new TaskName(this.taskName);
    }

    @Override
    public String toString() {
        return taskName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
        || (other instanceof TaskName // instanceof handles nulls
        && taskName.equals(((TaskName) other).taskName)); // state check
    }

    @Override
    public int hashCode() {
        return taskName.hashCode();
    }
}
