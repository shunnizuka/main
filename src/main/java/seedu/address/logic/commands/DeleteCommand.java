package seedu.address.logic.commands;

/**
 * Represents a delete command with hidden internal logic and the ability to be executed.
 */
public abstract class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String DELETE_EMPLOYEE_TYPE = "employee";
    public static final String DELETE_PROJECT_TYPE = "project";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " employee/project"
            + ": Deletes the employee/project identified by the index number/project name used in"
            + " the displayed employee/project list.\n"
            + "For delete employee:\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " employee" + " 1\n"
            + "For delete project:\n"
            + "Parameters: PROJECT_NAME\n"
            + "Example: " + COMMAND_WORD + " project" + " Apollo";
}
