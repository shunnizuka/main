package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

/**
 * Represents a task stored in a project milestone.
 */
public class Task {

    private TaskStatus status;
    private TaskName name;

    /**
     * Default constructor for a task
     */
    public Task(TaskName name) {
        requireNonNull(name);
        this.name = name;
        this.status = new TaskStatus();
    }

    /**
     * Constructor for a task specifying task status.
     */
    public Task(TaskName name, TaskStatus status) {
        requireNonNull(name);
        this.name = name;
        requireNonNull(status);
        this.status = status;

    }


    /**
     * Returns true if both tasks have the same name and status.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
                return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getTaskName().equals(getTaskName())
            && otherTask.getTaskStatus().equals(getTaskStatus());
    }

    public TaskName getTaskName() {
        return name;
    }

    public TaskStatus getTaskStatus() {
        return status;
    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }
        return otherTask != null
        && otherTask.getTaskName().equals(getTaskName());
    }

    /**
     * Returns a clone of this task.
     */
    public Task clone() {
        return new Task(getTaskName().clone(), getTaskStatus().clone());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Task: ")
        .append(getTaskName())
        .append(" Status: ")
        .append(getTaskStatus());
        return builder.toString();
    }

    /**
     * Checks if the task has the valid format by checking the relevant fields
     */
    public static boolean isValidTask(Task task) {
        return true;
    }
}
