package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class EditProjectUserStoryCommand extends EditProjectCommand {

    public static final String EDIT_USER_STORY_KEYWORD = "userstory";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " PROJECT_NAME " + EDIT_USER_STORY_KEYWORD
        + ": Edit the specified userstory in the list of userstories in project.\n"
        + "Example: " + COMMAND_WORD + " Apollo userstory 2 i/2 as a ... i want to .... so that .....";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return null;
    }
}
