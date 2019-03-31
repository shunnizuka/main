package seedu.address.testutil;

import seedu.address.model.project.ProjectTask;
import seedu.address.model.project.ProjectTaskName;
import seedu.address.model.project.ProjectTaskStatus;

/**
 * A utility class containing a list of {@code ProjectTask} objects to be used in tests.
 */
public class TypicalProjectTasks {
    public static final ProjectTask PROJECT_TASK_DO_SOMETHING = new ProjectTask(new ProjectTaskName("Do something"));
    public static final ProjectTask PROJECT_TASK_COMPLETED =
            new ProjectTask(new ProjectTaskName("Completed task"), new ProjectTaskStatus("complete"));

    private TypicalProjectTasks() {} // prevents instantiation
}
