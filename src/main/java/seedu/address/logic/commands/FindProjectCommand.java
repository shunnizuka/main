package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.employee.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindProjectCommand extends FindCommand {

    public static final String FIND_PROJECT_KEYWORD = "peoject";

    public static final String MESSAGE_USAGE = FindCommand.COMMAND_WORD + FIND_PROJECT_KEYWORD
        + "[ARGUMENTS]" + "\n"
        + ": Find all projects with names containing [ARGUMENTS]";

    private final NameContainsKeywordsPredicate predicate;

    public FindProjectCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW, model.getFilteredEmployeeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindProjectCommand // instanceof handles nulls
            && predicate.equals(((FindProjectCommand) other).predicate)); // state check
    }
}
