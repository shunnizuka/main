package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;

/**
 * Deletes a employee identified using it's displayed index from the address book.
 */
public class DeleteProjectCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " project"
            + ": Deletes the project identified by the name of the project.\n"
            + "Parameters: PROJECT_NAME\n"
            + "Example: " + COMMAND_WORD + " project" + " Apollo";

    public static final String MESSAGE_DELETE_EMPLOYEE_SUCCESS = "Deleted Employee: %1$s";

    private final Index targetIndex;

    public DeleteProjectCommand(Index targetIndex) {
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
                || (other instanceof DeleteProjectCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteProjectCommand) other).targetIndex)); // state check
    }
}
