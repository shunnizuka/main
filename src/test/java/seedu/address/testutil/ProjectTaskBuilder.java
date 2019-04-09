package seedu.address.testutil;

import seedu.address.model.project.ProjectTask;
import seedu.address.model.project.ProjectTaskName;
import seedu.address.model.project.Status;
import seedu.address.model.util.PocketProjectDate;

/**
 * A utility class to help with building Milestone objects.
 */
public class ProjectTaskBuilder {

    public static final String DEFAULT_TASK_NAME = "Create new feature";
    public static final String DEFAULT_STATUS = "ongoing";

    private ProjectTaskName taskName;
    private Status status;
    private PocketProjectDate completionDate;

    public ProjectTaskBuilder() {
        this.taskName = new ProjectTaskName(DEFAULT_TASK_NAME);
        this.status = new Status(DEFAULT_STATUS);
        this.completionDate = new PocketProjectDate();
    }

    /**
     * Initializes the ProjectTaskBuilder with the data of {@code taskToCopy}.
     */
    public ProjectTaskBuilder(ProjectTask taskToCopy) {
        this.taskName = new ProjectTaskName(taskToCopy.getTaskName());
        this.status = new Status(taskToCopy.getTaskStatus());
        this.completionDate = new PocketProjectDate(taskToCopy.getCompletionDate());
    }

    /**
     * Sets the {@code ProjectTaskName} of the {@code ProjectTask} that we are building.
     */
    public ProjectTaskBuilder withTaskName(String taskName) {
        this.taskName = new ProjectTaskName(taskName);
        return this;
    }

    /**
     * Sets the {@code ProjectStatus} of the {@code ProjectTask} that we are building.
     */
    public ProjectTaskBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code ProjectStatus} of the {@code ProjectTask} that we are building.
     */
    public ProjectTaskBuilder withCompletionDate(String date) {
        this.completionDate = new PocketProjectDate(date);
        return this;
    }

    public ProjectTask build() {
        return new ProjectTask(taskName, status, completionDate);
    }

}
