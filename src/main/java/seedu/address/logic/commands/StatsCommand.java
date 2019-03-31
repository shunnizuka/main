package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;

/**
 * Displays the number of ongoing and completed projects
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the number of ongoing and completed projects.\n";

    public static final String MESSAGE_STATS =
            "Number of ongoing projects: %d\nNumber of completed projects: %d\n";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        int numOngoing = model.getProjectList().size();
        int numCompleted = model.getCompletedProjectList().size();

        return new CommandResult(String.format(MESSAGE_STATS, numOngoing, numCompleted));

    }

    @Override
    public boolean equals(Object other) {
        return other instanceof StatsCommand;
    }
}
