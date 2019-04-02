package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddProjectCommand.MESSAGE_DUPLICATE_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Client;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Description;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;

/**
 * Edits the details (name, deadline, client, description) of an existing project in the pocket project.
 */
public class EditProjectDefaultCommand extends EditProjectCommand {

    public static final String EDIT_INFO_KEYWORD = "info";

    public static final String MESSAGE_USAGE = "Parameters: " + COMMAND_WORD + " PROJECT_NAME" + EDIT_INFO_KEYWORD
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_DEADLINE + "11/12/2019] "
        + "[" + PREFIX_CLIENT + "John] "
        + "[" + PREFIX_DESCRIPTION + "An application to manage projects] ";

    public static final String MESSAGE_EDIT_PROJECT_SUCCESS = "Edited Project: %1$s";
    public static final String MESSAGE_NOT_EDITED = "Project is not edited";

    private final ProjectName projectName;
    private final EditProjectDescriptor editProjectDescriptor;

    /**
     * @param projectName of the project in the project list to edit
     * @param editProjectDescriptor details to edit the employee with
     */
    public EditProjectDefaultCommand(ProjectName projectName, EditProjectDescriptor editProjectDescriptor) {
        requireNonNull(projectName);
        requireNonNull(editProjectDescriptor);

        this.projectName = projectName;
        this.editProjectDescriptor = new EditProjectDescriptor(editProjectDescriptor);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        List<Project> projectList = model.getProjectList();
        Project projectToEdit = null;
        for (Project p: projectList) {
            if (p.hasProjectName(projectName)) {
                projectToEdit = p;
            }
        }
        if (projectToEdit == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_NAME);
        }

        Project editedProject = createEditedProject(projectToEdit, editProjectDescriptor);

        if (!editedProject.isSameProject(projectToEdit) && model.hasProject(editedProject)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT);
        }

        model.setProject(projectToEdit, editedProject);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        model.commitPocketProject();
        return new CommandResult(String.format(MESSAGE_EDIT_PROJECT_SUCCESS, editedProject));
    }

    /**
     * Creates and returns a {@code Employee} with the details of {@code employeeToEdit}
     * edited with {@code editEmployeeDescriptor}.
     */
    private static Project createEditedProject(Project projectToEdit, EditProjectDescriptor editProjectDescriptor) {
        assert projectToEdit != null;

        ProjectName updatedName = editProjectDescriptor.getProjectName().orElse(projectToEdit.getProjectName());
        Deadline updatedDeadline = editProjectDescriptor.getDeadline().orElse(projectToEdit.getDeadline());
        Description updatedDescription = editProjectDescriptor.getDescription().orElse(projectToEdit.getDescription());
        Client updatedClient = editProjectDescriptor.getClient().orElse(projectToEdit.getClient());

        return projectToEdit.editProject(updatedName, updatedClient, updatedDeadline, updatedDescription);
    }

    /**
     * Stores the details to edit the project with. Each non-empty field value will replace the
     * corresponding field value of the project.
     */
    public static class EditProjectDescriptor {

        private ProjectName projectName;
        private Deadline deadline;
        private Description description;
        private Client client;

        public EditProjectDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code skills} is used internally.
         */
        public EditProjectDescriptor(EditProjectDescriptor toCopy) {
            setProjectName(toCopy.projectName);
            setDeadline(toCopy.deadline);
            setDescription(toCopy.description);
            setClient(toCopy.client);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(projectName, deadline, description, client);
        }

        public void setProjectName(ProjectName projectName) {
            this.projectName = projectName;
        }

        public Optional<ProjectName> getProjectName() {
            return Optional.ofNullable(projectName);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setClient(Client client) {
            this.client = client;
        }

        public Optional<Client> getClient() {
            return Optional.ofNullable(client);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditProjectDescriptor)) {
                return false;
            }

            // state check
            EditProjectDescriptor e = (EditProjectDescriptor) other;

            return getProjectName().equals(e.getProjectName())
                && getDeadline().equals(e.getDeadline())
                && getDescription().equals(e.getDescription())
                && getClient().equals(e.getClient());
        }
    }
}

