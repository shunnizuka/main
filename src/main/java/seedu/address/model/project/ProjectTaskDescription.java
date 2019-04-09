package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Description of the project task in the Pocket Project.
 */

public class ProjectTaskDescription extends Description{

    public final String taskName;

    /**
     * Constructor for ProjectTaskDescription.
     */
    public ProjectTaskDescription(String taskName) {
        requireNonNull(taskName);
        checkArgument(isValidDescription(taskName), MESSAGE_CONSTRAINTS);
        this.taskName = taskName;
    }

    public String getTaskName() {
        return this.taskName;
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
