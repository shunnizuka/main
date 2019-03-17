package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;

/**
 * Deletes a employee identified using it's displayed index from the address book.
 */
public class DeleteProjectCommand extends DeleteCommand {

    public static final String DELETE_PROJECT_KEYWORD = "project";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " project"
            + ": Deletes the project identified by the name of the project.\n"
            + "Parameters: PROJECT_NAME\n"
            + "Example: " + COMMAND_WORD + " project" + " Apollo";

    public static final String MESSAGE_DELETE_PROJECT_SUCCESS = "Deleted Project: %1$s";

    private final ProjectName projectName;

    public DeleteProjectCommand(ProjectName targetName) {
        this.projectName = targetName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Project> projectList = model.getProjectList();
        Project projectToDelete = null;
        boolean found = false;
        for (Project p: projectList) {
            if (p.hasProjectName(projectName)) {
                found = true;
                projectToDelete = p;
                model.deleteProject(projectToDelete);
                model.commitPocketProject();

            }
        }
        if (!found) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_NAME);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteProjectCommand // instanceof handles nulls
                && projectName.equals(((DeleteProjectCommand) other).projectName)); // state check
    }
}
