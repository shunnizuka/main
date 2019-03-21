package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEmployeeAtIndex;
import static seedu.address.testutil.TypicalEmployees.getTypicalPocketProjectWithEmployees;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EMPLOYEE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EMPLOYEE;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewCommand}.
 */
public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalPocketProjectWithEmployees(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPocketProjectWithEmployees(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Index lastPersonIndex = Index.fromOneBased(model.getFilteredEmployeeList().size());

        assertExecutionSuccess(INDEX_FIRST_EMPLOYEE);
        assertExecutionSuccess(INDEX_THIRD_EMPLOYEE);
        assertExecutionSuccess(lastPersonIndex);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredEmployeeList().size() + 1);

        assertExecutionFailure(outOfBoundsIndex, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showEmployeeAtIndex(model, INDEX_FIRST_EMPLOYEE);
        showEmployeeAtIndex(expectedModel, INDEX_FIRST_EMPLOYEE);

        assertExecutionSuccess(INDEX_FIRST_EMPLOYEE);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        showEmployeeAtIndex(model, INDEX_FIRST_EMPLOYEE);
        showEmployeeAtIndex(expectedModel, INDEX_FIRST_EMPLOYEE);

        Index outOfBoundsIndex = INDEX_SECOND_EMPLOYEE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundsIndex.getZeroBased() < model.getPocketProject().getEmployeeList().size());

        assertExecutionFailure(outOfBoundsIndex, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommand selectFirstCommand = new ViewCommand(INDEX_FIRST_EMPLOYEE);
        ViewCommand selectSecondCommand = new ViewCommand(INDEX_SECOND_EMPLOYEE);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns true
        ViewCommand selectFirstCommandCopy = new ViewCommand(INDEX_FIRST_EMPLOYEE);
        assertTrue(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different employee -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
    }

    /**
     * Executes a {@code ViewCommand} with the given {@code index},
     * and checks that the model's selected employee is set to the employee at {@code index} in the filtered employee
     * list.
     */
    private void assertExecutionSuccess(Index index) {
        ViewCommand viewCommand = new ViewCommand(index);
        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_EMPLOYEE_SUCCESS, index.getOneBased());
        expectedModel.setSelectedEmployee(model.getFilteredEmployeeList().get(index.getZeroBased()));

        assertCommandSuccess(viewCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    /**
     * Executes a {@code ViewCommand} with the given {@code index}, and checks that a {@code CommandException}
     * is thrown with the {@code expectedMessage}.
     */
    private void assertExecutionFailure(Index index, String expectedMessage) {
        ViewCommand viewCommand = new ViewCommand(index);
        assertCommandFailure(viewCommand, model, commandHistory, expectedMessage);
    }
}
