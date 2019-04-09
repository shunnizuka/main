package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.ProjectTask;
import seedu.address.model.project.ProjectTaskName;
import seedu.address.model.project.Status;
import seedu.address.model.util.PocketProjectDate;

/**
 * Jackson-friendly version of {@link ProjectTask}.
 */
class JsonAdaptedProjectTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project task's %s field is missing!";

    private final String taskName;
    private final String taskStatus;
    private final String completionDate;

    /**
     * Constructs a {@code JsonAdaptedProjectTask} with the given user story details.
     */
    @JsonCreator
    public JsonAdaptedProjectTask(@JsonProperty("taskName") String taskName,
                                  @JsonProperty("taskStatus") String taskStatus,
                                  @JsonProperty("completionDate") String completionDate) {
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.completionDate = completionDate;
    }

    /**
     * Converts a given {@code ProjectTask} into this class for Jackson use.
     */
    public JsonAdaptedProjectTask(ProjectTask source) {
        this.taskName = source.getTaskName();
        this.taskStatus = source.getTaskStatus();
        this.completionDate = source.getCompletionDate();
    }

    /**
     * Converts this Jackson-friendly adapted project task object into the model's {@code ProjectTask} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted project task.
     */
    public ProjectTask toModelType() throws IllegalValueException {

        if (taskName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProjectTaskName.class.getSimpleName()));
        }
        if (!ProjectTaskName.isValidTaskName(taskName)) {
            throw new IllegalValueException(ProjectTaskName.MESSAGE_CONSTRAINTS);
        }
        final ProjectTaskName modelProjectTaskName = new ProjectTaskName(taskName);

        if (taskStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
            Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(taskStatus)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelProjectTaskStatus = new Status(taskStatus);

        if (completionDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
            PocketProjectDate.class.getSimpleName()));
        }
        if (!PocketProjectDate.isValidDate(completionDate)) {
            throw new IllegalValueException(PocketProjectDate.MESSAGE_CONSTRAINTS);
        }
        final PocketProjectDate modelProjectTaskCompletionDate = new PocketProjectDate(completionDate);

        return new ProjectTask(modelProjectTaskName, modelProjectTaskStatus, modelProjectTaskCompletionDate);
    }

}
