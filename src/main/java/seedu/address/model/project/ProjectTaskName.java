package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

/**
 * Name of the project task in the Pocket Project.
 */

public class ProjectTaskName {

    public static final String MESSAGE_CONSTRAINTS =
            "Project task names should not be empty or consisting of only spaces.";

    public final String taskName;

    /**
     * Constructor for ProjectTaskName.
     */
    public ProjectTaskName(String taskName) {
        requireNonNull(taskName);
        this.taskName = taskName;
    }

    /**
     * Returns true if given string is valid for a task name string
     */
    public static boolean isValidTaskName(String info) {
        return !info.trim().isEmpty();
    }

    /**
     * Returns a clone of this TaskName object.
     */
    public ProjectTaskName clone() {
        return new ProjectTaskName(this.taskName);
    }

    @Override
    public String toString() {
        return taskName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ProjectTaskName // instanceof handles nulls
            && taskName.equals(((ProjectTaskName) other).taskName)); // state check
    }

    @Override
    public int hashCode() {
        return taskName.hashCode();
    }
}
