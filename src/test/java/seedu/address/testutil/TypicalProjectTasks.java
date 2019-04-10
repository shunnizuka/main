package seedu.address.testutil;

import seedu.address.model.project.ProjectTask;

/**
 * A utility class containing a list of {@code ProjectTask} objects to be used in tests.
 */
public class TypicalProjectTasks {

    public static final ProjectTask PROJECT_TASK_DO_SOMETHING = new ProjectTaskBuilder()
        .withTaskName("Do something")
        .withStatus("ongoing")
        .build();

    public static final ProjectTask PROJECT_TASK_ONGOING = new ProjectTaskBuilder()
        .withTaskName("Ongoing task")
        .withStatus("ongoing")
        .build();

    public static final ProjectTask PROJECT_TASK_ONGOING_STATUS_CHANGE = new ProjectTaskBuilder()
        .withTaskName("Ongoing task")
        .withStatus("complete")
        .build();

    public static final ProjectTask PROJECT_TASK_COMPLETED = new ProjectTaskBuilder()
        .withTaskName("Completed task")
        .withStatus("complete")
        .withCompletionDate("15/12/2019")
        .build();

    public static final ProjectTask PROJECT_TASK_ON_HOLD = new ProjectTaskBuilder()
        .withTaskName("On hold task")
        .withStatus("on hold")
        .withCompletionDate("03/02/2019")
        .build();

    private TypicalProjectTasks() {} // prevents instantiation
}
