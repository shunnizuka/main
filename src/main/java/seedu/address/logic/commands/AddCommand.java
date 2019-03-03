package seedu.address.logic.commands;

/**
 * Represents an add command with hidden internal logic and the ability to be executed.
 */
public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";


    public static final String MESSAGE_USAGE = COMMAND_WORD + " employee/project"
            + ": Adds the employee/project identified by the index number/project name used in"
            + " the displayed employee/project list.\n"
            + AddEmployeeCommand.MESSAGE_USAGE + "\n";
            //TODO  + AddProjectCommand.MESSAGE_USAGE;

}
