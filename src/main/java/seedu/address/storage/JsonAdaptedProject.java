package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.Client;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Description;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;

/**
 * Jackson-friendly version of {@link Project}.
 */
class JsonAdaptedProject {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";

    private final String projectName;
    private final String client;
    private final String deadline;
    private final String description;
    private final List<JsonAdaptedMilestone> milestones = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProject} with the given project details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("projectName") String projectName, @JsonProperty("client") String client,
                              @JsonProperty("deadline") String deadline,
                              @JsonProperty("description") String description,
                              @JsonProperty("milestones") List<JsonAdaptedMilestone> milestones) {
        this.projectName = projectName;
        this.client = client;
        this.deadline = deadline;
        this.description = description;
        if (milestones != null) {
            this.milestones.addAll(milestones);
        }
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        projectName = source.getProjectName().projectName;
        client = source.getClient().client;
        deadline = source.getDeadline().deadline;
        description = source.getDescription().description;
        milestones.addAll(source.getMilestones().stream()
            .map(JsonAdaptedMilestone::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted project object into the model's {@code Project} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted project.
     */
    public Project toModelType() throws IllegalValueException {
        final List<Milestone> projectMilestones = new ArrayList<>();
        for (JsonAdaptedMilestone milestone : milestones) {
            projectMilestones.add(milestone.toModelType());
        }

        if (projectName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ProjectName.class.getSimpleName()));
        }
        if (!ProjectName.isValidName(projectName)) {
            throw new IllegalValueException(ProjectName.MESSAGE_CONSTRAINTS);
        }
        final ProjectName modeProjectlName = new ProjectName(projectName);

        if (client == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Client.class.getSimpleName()));
        }
        if (!Client.isValidName(client)) {
            throw new IllegalValueException(Client.MESSAGE_CONSTRAINTS);
        }
        final Client modelClient = new Client(client);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Description.class.getSimpleName()));
        }
        final Description modelDescription = new Description(description);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDate(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);



        final List<Milestone> modelMilestones = projectMilestones;
        return new Project(modeProjectlName, modelClient, modelDeadline, modelMilestones, modelDescription);
    }

}
