package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

/**
 * Lists all employees in the employees list to the user.
 */
public class ListEmployeeCommand extends ListCommand {

    public static final String LIST_EMPLOYEE_KEYWORD = "employee";

    public static final String MESSAGE_SUCCESS = "Listed all employees";

    public static final String MESSAGE_USAGE = ListCommand.COMMAND_WORD
            + LIST_EMPLOYEE_KEYWORD
            + "\n"
            + ": Lists all employees registered in Pocket Project\n";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
