package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddMilestoneToCommand;
import seedu.address.logic.commands.AddProjectCommand;
import seedu.address.logic.commands.AddToCommand;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.Project;
import systemtests.PocketProjectSystemTest;

/**
 * A utility class for project.
 */
public class ProjectUtil extends PocketProjectSystemTest {

    /**
     * Returns an add command string for adding the {@code project}.
     */
    public static String getAddProjectCommand(Project project) {
        return AddCommand.COMMAND_WORD + " " + AddProjectCommand.ADD_PROJECT_KEYWORD + " "
                + getProjectDetails(project);
    }

    /**
     * Returns the part of command string for the given {@code project}'s details.
     */
    public static String getProjectDetails(Project project) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + project.getProjectName().projectName + " ");
        sb.append(PREFIX_CLIENT + project.getClient().client + " ");
        sb.append(PREFIX_DEADLINE + project.getDeadline().deadline + " ");
        return sb.toString();
    }

    /**
     * Returns an add milestone to command string for adding the {@code milestone} to {@code project}.
     */
    public static String getAddMilestoneToCommand(Project project, Milestone milestone) {
        return AddToCommand.COMMAND_WORD + " " + project.getProjectName().projectName + " "
            + AddMilestoneToCommand.ADD_MILESTONE_KEYWORD + " " + getMilestoneDetails(milestone);
    }

    /**
     * Returns the part of command string for the given {@code milestone}'s details.
     */
    public static String getMilestoneDetails(Milestone milestone) {
        StringBuilder sb = new StringBuilder();
        sb.append(milestone.getMilestone() + " ");
        sb.append(milestone.getDate() + " ");
        return sb.toString();
    }

}
