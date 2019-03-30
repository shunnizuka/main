package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
//import seedu.address.model.project.Milestone;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.Task;

/**
 * Adds a milestone to a project in the projects list.
 */
public class AddTaskToCommand extends AddToCommand {

    public static final String ADD_TASK_KEYWORD = "task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " PROJECT_NAME task n/TASK_NAME m/MILESTONE_INDEX"
            + ": adds the specified task to the list of tasks in a project's milestone specified by index.\n"
            + "Example: " + COMMAND_WORD + " Apollo task n/Create feature XYZ m/1";

    public static final String MESSAGE_COMMAND_EXECUTED = "Command is executed.";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "Added task: %1$s from %2$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in this project milestone.";

    private final ProjectName targetProjectName;
    private final Task taskToAdd;
    //private final Milestone milestone;

    public AddTaskToCommand(ProjectName targetProject, Task task) {
        //requireNonNull(milestone);
        requireNonNull(targetProject);
        requireNonNull(task);
        //this.milestone = milestone;
        this.taskToAdd = task;
        this.targetProjectName = targetProject;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        throw new CommandException(MESSAGE_COMMAND_EXECUTED);
        /*requireNonNull(model);

        Project targetProject = null;
        List<Project> projectList = model.getProjectList();
        for (Project p: projectList) {
            if (p.hasProjectName(targetProjectName)) {
                targetProject = p;
            }
        }
        if (targetProject == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_NAME);
        }

        List<Milestone> milestoneList = targetProject.getMilestones();
        if (milestoneList.contains(milestoneToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MILESTONE);
        }
        targetProject.addMilestone(milestoneToAdd);
        model.commitPocketProject();
        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, taskToAdd,
        targetProject.getProjectName()));*/
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskToCommand // instanceof handles nulls
                && targetProjectName.equals(((AddTaskToCommand) other).targetProjectName))
                && taskToAdd.equals(((AddTaskToCommand) other).taskToAdd); // state check
    }
}
