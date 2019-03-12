package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.project.ProjectName;

/**
 * Removes an employee identified using it's displayed index from a project in the address book.
 */
public class RemoveEmployeeFromCommand extends RemoveFromCommand {

    public static final String REMOVE_EMPLOYEE_KEYWORD = "employee";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "PROJECT_NAME employee"
            + ": removes the employee identified by the index number"
            + " used in the displayed employee list from the project.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + "Apollo employee 1";

    public static final String MESSAGE_DELETE_EMPLOYEE_SUCCESS = "Removed Employee: %1$s from %2$s";

    private final Index targetIndex;
    private final ProjectName targetProject;

    public RemoveEmployeeFromCommand(Index targetIndex, ProjectName targetProject) {
        this.targetProject = targetProject;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Employee> lastShownList = model.getFilteredEmployeeList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
        }

        Employee employeeToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteEmployee(employeeToDelete);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_DELETE_EMPLOYEE_SUCCESS, employeeToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteEmployeeCommand // instanceof handles nulls
                && targetIndex.equals(((RemoveEmployeeFromCommand) other).targetIndex)); // state check
    }
}
