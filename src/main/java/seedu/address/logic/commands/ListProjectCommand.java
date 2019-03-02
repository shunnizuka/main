package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.employee.NameContainsKeywordsPredicate;

/**
 * Lists all projects in the projects list to the user.
 */
public class ListProjectCommand extends ListCommand {

    public static final String LIST_PROJECT_KEYWORD = "project";

    public static final String MESSAGE_SUCCESS = "Listed all projects";

    public static final String MESSAGE_USAGE = ListCommand.COMMAND_WORD
            + LIST_PROJECT_KEYWORD
            + "\n"
            + ": Lists all projects registered in Pocket Project\n";

    public ListCommandEmployeeCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
