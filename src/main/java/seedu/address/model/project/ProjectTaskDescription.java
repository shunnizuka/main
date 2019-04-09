package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Name of the project task in the Pocket Project.
 */

public class ProjectTaskDescription {

    public static final String MESSAGE_CONSTRAINTS =
            "Project task names should not be empty or consisting of only spaces.";

    public final String taskName;

    /**
     * Constructor for ProjectTaskDescription.
     */
    public ProjectTaskDescription(String taskName) {
        requireNonNull(taskName);
        checkArgument(isValidTaskName(taskName), MESSAGE_CONSTRAINTS);
        this.taskName = taskName;
    }

    public String getTaskName() {
        return this.taskName;
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
    public ProjectTaskDescription clone() {
        return new ProjectTaskDescription(this.taskName);
    }

    @Override
    public String toString() {
        return taskName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ProjectTaskDescription // instanceof handles nulls
            && taskName.equals(((ProjectTaskDescription) other).taskName)); // state check
    }

    @Override
    public int hashCode() {
        return taskName.hashCode();
    }
}
