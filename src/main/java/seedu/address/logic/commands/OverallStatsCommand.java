package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.util.SearchingUtil;

/**
 * Displays a summary of all the projects
 */
public class OverallStatsCommand extends StatsCommand {

    public static final String MESSAGE_USAGE = StatsCommand.COMMAND_WORD
            + ": Displays A summary of the projects and employees.\n";

    public static final String MESSAGE_STATS =
            "Number of ongoing projects: %d\nNumber of completed projects: %d\n"
            + "%s\n"
            + "%s\n"
            + "%s\n"
            + "%s\n"
            + "%s\n";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        int numOngoing = model.getProjectList().size();
        int numCompleted = model.getCompletedProjectList().size();
        String projectWithMostEmployeeString = SearchingUtil.projectWithMostEmployee(model.getProjectList());
        String projectWithEarliestDeadlineString = SearchingUtil.projectWithEarliestDeadline(model.getProjectList());
        String employeeWithLeastProjectString = SearchingUtil
                .employeeWithLeastProject(model.getPocketProject().getEmployeeList());
        String employeeWithMostProjectString = SearchingUtil
                .employeeWithMostProject(model.getPocketProject().getEmployeeList());
        String projectWithLeastEmployeeString = SearchingUtil.projectWithLeastEmployee(model.getProjectList());
        return new CommandResult(
                String.format(MESSAGE_STATS, numOngoing, numCompleted, projectWithEarliestDeadlineString,
                projectWithLeastEmployeeString, projectWithMostEmployeeString, employeeWithLeastProjectString,
                employeeWithMostProjectString));

    }

    @Override
    public boolean equals(Object other) {
        return other instanceof OverallStatsCommand;
    }
}
