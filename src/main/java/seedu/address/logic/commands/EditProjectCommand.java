package seedu.address.logic.commands;

/**
 * Represents a EditProject command with hidden internal logic and the ability to be executed.
 */
public abstract class EditProjectCommand extends Command {

    public static final String COMMAND_WORD = "editproject";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " PROJECT_NAME milestone/userstory/info ARGUMENTS"
        + ": adds the employee/milestone/user story into the selected project.\n";

}
