package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TestUtil;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code AddEmployeeToCommand}.
 */
public class AddEmployeeToCommandTest {

    private Model model = new ModelManager(TestUtil.typicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();


}
