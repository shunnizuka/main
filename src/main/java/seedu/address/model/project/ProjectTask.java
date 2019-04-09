package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

/**
 * Represents a project task stored in a project milestone.
 */
public class ProjectTask {

    private ProjectTaskStatus status;
    private ProjectTaskDescription name;

    /**
     * Default constructor for a project task
     */
    public ProjectTask(ProjectTaskDescription name) {
        requireNonNull(name);
        this.name = name;
        this.status = new ProjectTaskStatus();
    }

    /**
     * Constructor for a project task specifying project task status.
     */
    public ProjectTask(ProjectTaskDescription name, ProjectTaskStatus status) {
        requireNonNull(name);
        this.name = name;
        requireNonNull(status);
        this.status = status;
    }

    public ProjectTaskDescription getTaskName() {
        return name;
    }

    public ProjectTaskStatus getTaskStatus() {
        return status;
    }

    public String getTaskStatusString() {
        return getTaskStatus().getTaskStatusString();
    }

    public String getTaskNameString() {
        return getTaskName().getTaskName();
    }

    public boolean isComplete() {
        return getTaskStatus().isComplete();
    }
    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(ProjectTask otherTask) {
        if (otherTask == this) {
            return true;
        }
        return otherTask != null
            && otherTask.getTaskName().equals(getTaskName());
    }

    /**
     * Returns a clone of this task.
     */
    public ProjectTask clone() {
        return new ProjectTask(getTaskName().clone(), getTaskStatus().clone());
    }

    /**
     * Returns true if both project tasks have the same name and status.
     * This defines a stronger notion of equality between two project tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ProjectTask)) {
            return false;
        }

        ProjectTask otherTask = (ProjectTask) other;
        return otherTask.getTaskName().equals(getTaskName())
            && otherTask.getTaskStatus().equals(getTaskStatus());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Project Task: ")
                .append(getTaskName())
                .append(" (Status: ")
                .append(getTaskStatus())
                .append(") ");
        return builder.toString();
    }

    /**
     * Checks if the task has the valid format by checking the relevant fields
     */
    public static boolean isValidTask(ProjectTask task) {
        return ProjectTaskDescription.isValidTaskName(task.getTaskName().taskName)
            && ProjectTaskStatus.isValidTaskStatus(task.getTaskStatus().taskStatus);
    }
}
