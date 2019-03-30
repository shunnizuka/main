package seedu.address.logic.commands;

/**
 * Represents a EditProject comman with hidden internal logic and the ability to be executed.
 */
public abstract class EditProjectCommand extends Command {

    public static final String COMMAND_WORD = "editproj";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " PROJECT_NAME milestone/userstory ARGUMENTS"
        + " or " + EditProjectDefaultCommand.MESSAGE_USAGE
        + ": adds the employee/milestone/user story into the selected project.\n";
    
    
}
