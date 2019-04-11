package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.util.PocketProjectDate;

/**
 * Represents a project task stored in a project milestone.
 */
public class ProjectTask {

    private Status status;
    private ProjectTaskDescription description;
    private PocketProjectDate completionDate;

    /**
     * Default constructor for a project task
     */
    public ProjectTask(ProjectTaskDescription description) {
        this(description, new Status(), new PocketProjectDate());
    }

    /**
     * Constructor for a project task specifying project task status.
     */
    public ProjectTask(ProjectTaskDescription description, Status status, PocketProjectDate date) {
        requireAllNonNull(description, status, date);
        this.description = description;
        this.status = status;
        this.completionDate = date;
    }

    public String getTaskDescription() {
        return this.description.getTaskDescription();
    }

    public String getTaskStatus() {
        return this.status.getStatus();
    }

    public String getCompletionDate() {
        return this.completionDate.getDate();
    }

    public boolean isComplete() {
        return this.status.isComplete();
    }

    /**
     * Returns true if both tasks have the same description.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(ProjectTask otherTask) {
        if (otherTask == this) {
            return true;
        }
        return otherTask != null
            && otherTask.getTaskDescription().equals(getTaskDescription());
    }

    /**
     * Returns a clone of this task.
     */
    public ProjectTask clone() {
        return new ProjectTask(this.description.clone(), this.status.clone(), this.completionDate.clone());
    }

    /**
     * Returns true if both project tasks have the same description and status.
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
        return otherTask.description.equals(this.description)
            && otherTask.status.equals(this.status)
            && otherTask.completionDate.equals(this.completionDate);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Project Task: ")
                .append(getTaskDescription())
                .append(" (Status: ")
                .append(getTaskStatus())
                .append(") ");
        return builder.toString();
    }

    /**
     * Checks if the task has the valid format by checking the relevant fields
     */
    public static boolean isValidTask(ProjectTask task) {
        return ProjectTaskDescription.isValidDescription(task.getTaskDescription())
            && Status.isValidStatus(task.getTaskStatus())
            && PocketProjectDate.isValidDate(task.getCompletionDate());
    }

    /**
     * Updates project task status to the new status.
     */
    public void updateStatus(Status newStatus) {
        this.status = newStatus;
        if (isComplete()) {
            this.completionDate = new PocketProjectDate();
        }
    }
}
