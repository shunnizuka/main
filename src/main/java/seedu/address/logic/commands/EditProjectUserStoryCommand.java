package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

/**
 * Edits the existing user story of an exisiting project in pocket project
 */
public class EditProjectUserStoryCommand extends EditProjectCommand {

    public static final String EDIT_USER_STORY_KEYWORD = "userstory";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " PROJECT_NAME " + EDIT_USER_STORY_KEYWORD
        + ": Edit the specified userstory in the list of userstories in project.\n"
        + "Example: " + COMMAND_WORD + " Apollo userstory 2 i/2 as a ... i want to .... so that .....";

    //TODO implement
    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return null;
    }
}
