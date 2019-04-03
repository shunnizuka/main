package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDate;
import seedu.address.model.project.ProjectName;

/**
 * Deletes a project identified using it's displayed index/name from the pocket project.
 */
public class CompleteCommand extends Command {

    public static final String COMMAND_WORD = "complete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Completes the project identified by the name or index(must be positive integer) of the project.\n"
            + "Parameters: PROJECT_NAME/PROJECT_INDEX\n"
            + "Example: " + COMMAND_WORD + " Apollo\n"
            + "Example: " + COMMAND_WORD + " 1\n";

    public static final String MESSAGE_COMPLETE_PROJECT_SUCCESS = "Completed Project: %1$s";

    private final ProjectName projectName;
    private final Index targetIndex;
    private final ProjectDate completionDate;

    public CompleteCommand(ProjectName targetName, ProjectDate completionDate) {
        this.projectName = targetName;
        this.targetIndex = null;
        this.completionDate = completionDate;
    }

    public CompleteCommand(Index index, ProjectDate completionDate) {
        this.targetIndex = index;
        this.projectName = null;
        this.completionDate = completionDate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        ProjectName targetName = this.projectName;
        if (targetName == null) {
            List<Project> lastShownList = model.getFilteredProjectList();
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
            }
            targetName = lastShownList.get(targetIndex.getZeroBased()).getProjectName();
        }
        Project projectToComplete = model.getProjectWithName(targetName);
        if (projectToComplete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_NAME);
        }
        model.completeProject(projectToComplete, this.completionDate);
        model.commitPocketProject();

        return new CommandResult(String.format(MESSAGE_COMPLETE_PROJECT_SUCCESS, projectToComplete));
    }

    @Override
    public boolean equals(Object other) {
        if (projectName != null) {
            return other == this // short circuit if same object
                    || (other instanceof CompleteCommand // instanceof handles nulls
                    && projectName.equals(((CompleteCommand) other).projectName)
                    && completionDate.equals(((CompleteCommand) other).completionDate));
            // state check
        } else {
            return other == this // short circuit if same object
                    || (other instanceof CompleteCommand // instanceof handles nulls
                    && targetIndex.equals(((CompleteCommand) other).targetIndex)
                    && completionDate.equals(((CompleteCommand) other).completionDate)); // state check
        }
    }
}
