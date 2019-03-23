package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddProjectCommand;
import seedu.address.model.project.Project;

/**
 * A utility class for project.
 */
public class ProjectUtil {

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
}
