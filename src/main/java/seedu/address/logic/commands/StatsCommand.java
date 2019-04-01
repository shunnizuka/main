package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.util.SearchingUtil;

/**
 * Displays the number of ongoing and completed projects
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the number of ongoing and completed projects.\n";

    public static final String MESSAGE_STATS =
            "Number of ongoing projects: %d\nNumber of completed projects: %d\n"
            + "%s\n"
            + "%s\n";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        int numOngoing = model.getProjectList().size();
        int numCompleted = model.getCompletedProjectList().size();
        String projectWithMostEmployeeString = SearchingUtil.projectWithMostEmployee(model.getProjectList());
        String projectWithEarliestDeadlineString = SearchingUtil.projectWithEarliestDeadline(model.getProjectList());

        return new CommandResult(String.format(MESSAGE_STATS, numOngoing, numCompleted,
                projectWithEarliestDeadlineString, projectWithMostEmployeeString));

    }

    @Override
    public boolean equals(Object other) {
        return other instanceof StatsCommand;
    }
}
