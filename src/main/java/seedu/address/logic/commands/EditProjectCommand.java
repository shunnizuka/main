package seedu.address.logic.commands;

/**
 * Represents a EditProject command with hidden internal logic and the ability to be executed.
 */
public abstract class EditProjectCommand extends EditCommand {

    public static final String EDIT_PROJECT_KEYWORD = "project";

    public static final String MESSAGE_USAGE = EDIT_PROJECT_KEYWORD + " PROJECT_NAME milestone/userstory/info ARGUMENTS"
        + ": adds the employee/milestone/user story into the selected project.\n";

}
