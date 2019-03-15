package seedu.address.logic.commands;

/**
 * Represents a RemoveFrom command with hidden internal logic and the ability to be executed.
 */
public abstract class RemoveFromCommand extends Command {
    public static final String COMMAND_WORD = "removefrom";


    public static final String MESSAGE_USAGE = COMMAND_WORD + " PROJECT_NAME employee/milestone"
            + ": removes the employee/milestone identified by the index number used in"
            + " the displayed employee/milestone list.\n"
            + RemoveEmployeeFromCommand.MESSAGE_USAGE + "\n"
            + RemoveMilestoneFromCommand.MESSAGE_USAGE;
}
