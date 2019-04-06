package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Description;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.exceptions.DuplicateMilestoneException;
import seedu.address.model.util.PocketProjectDate;

/**
 * Adds a milestone to a project in the projects list.
 */
public class AddMilestoneToCommand extends AddToCommand {

    public static final String ADD_MILESTONE_KEYWORD = "milestone";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " PROJECT_NAME milestone"
            + ": add the specified milestone to the list of milestones in project.\n"
            + "Example: " + COMMAND_WORD + " Apollo milestone m/Finished UserGuide d/23/04/2019";

    public static final String MESSAGE_ADD_MILESTONE_SUCCESS = "Added milestone: %1$s to %2$s";
    public static final String MESSAGE_DUPLICATE_MILESTONE = "This milestone already exists in the PocketProject.";

    private final ProjectName targetProjectName;
    private final Milestone milestoneToAdd;

    public AddMilestoneToCommand(ProjectName targetProject, Milestone milestone) {
        requireNonNull(targetProject);
        requireNonNull(milestone);

        this.milestoneToAdd = milestone;
        this.targetProjectName = targetProject;

    }


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        Project targetProject = model.getProjectWithName(targetProjectName);
        if (targetProject == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_NAME);
        }

        try {
            targetProject.addMilestone(milestoneToAdd);
        } catch (DuplicateMilestoneException e) {
            throw new CommandException(MESSAGE_DUPLICATE_MILESTONE);
        }
        model.commitPocketProject();
        return new CommandResult(String.format(MESSAGE_ADD_MILESTONE_SUCCESS, milestoneToAdd,
            targetProject.getProjectName()));


    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMilestoneToCommand // instanceof handles nulls
                && targetProjectName.equals(((AddMilestoneToCommand) other).targetProjectName))
                && milestoneToAdd.equals(((AddMilestoneToCommand) other).milestoneToAdd); // state check
    }


}
