package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.ProjectName;

/**
 * Displays a summary of the progress of a project
 */
public class IndividualStatsCommand extends StatsCommand {

    public static final String MESSAGE_USAGE = StatsCommand.COMMAND_WORD
            + ": Displays A summary of the progress of a project.\n";

    private final ProjectName projectName;
    private final Index targetIndex;

    public IndividualStatsCommand(ProjectName targetName) {
        this.projectName = targetName;
        this.targetIndex = null;
    }

    public IndividualStatsCommand(Index index) {
        this.targetIndex = index;
        this.projectName = null;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof OverallStatsCommand;
    }
}
