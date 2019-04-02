package seedu.address.logic.commands;

/**
 * Represent an edit command with hidden internal logic and the ability to be executed.
 */
public abstract class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = "Edit command";

}
