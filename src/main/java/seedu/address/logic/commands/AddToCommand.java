package seedu.address.logic.commands;

/**
 * Represents a AddTo command with hidden internal logic and the ability to be executed.
 */
public abstract class AddToCommand extends Command {

    public static final String COMMAND_WORD = "addto";


    public static final String MESSAGE_USAGE = COMMAND_WORD + " PROJECT_NAME employee/milestone"
            + ": adds the employee/milestone into the respective lists stored under the stated project.\n"
            + AddEmployeeToCommand.MESSAGE_USAGE + "\n"
            + AddMilestoneToCommand.MESSAGE_USAGE;
}
