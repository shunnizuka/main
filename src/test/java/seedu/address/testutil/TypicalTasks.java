package seedu.address.testutil;

import seedu.address.model.project.Task;
import seedu.address.model.project.TaskName;
import seedu.address.model.project.TaskStatus;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task TASK_DO_SOMETHING = new Task(new TaskName("Do something"));
    public static final Task TASK_COMPLETED = new Task(new TaskName("Completed task"), new TaskStatus(true));

    private TypicalTasks() {} // prevents instantiation
}
