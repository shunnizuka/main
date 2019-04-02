package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class EditProjectMilestoneCommand extends EditProjectCommand {

    public static final String EDIT_MILESTONE_KEYWORD = "milestone";
    
    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return null;
    }
}
