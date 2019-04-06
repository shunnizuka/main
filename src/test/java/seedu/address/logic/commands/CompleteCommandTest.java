package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalProjects.getTypicalPocketProjectWithProjects;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.util.PocketProjectDate;
import seedu.address.testutil.TypicalProjectNames;
import seedu.address.testutil.TypicalProjects;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code CompleteCommand}.
 */
public class CompleteCommandTest {
    private static final PocketProjectDate VALID_DATE = new PocketProjectDate("11/11/2011");
    private Model model = new ModelManager(getTypicalPocketProjectWithProjects(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        CompleteCommand completeCommand = new CompleteCommand(INDEX_FIRST_PROJECT, VALID_DATE);
        String expectedMessage = String.format(CompleteCommand.MESSAGE_COMPLETE_PROJECT_SUCCESS, projectToDelete);

        ModelManager expectedModel = new ModelManager(model.getPocketProject(), new UserPrefs());
        expectedModel.completeProject(projectToDelete, VALID_DATE);
        expectedModel.commitPocketProject();

        assertCommandSuccess(completeCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        CompleteCommand completeCommand = new CompleteCommand(outOfBoundIndex, VALID_DATE);

        assertCommandFailure(completeCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }
    @Test
    public void execute_validName_success() {
        Project projectToDelete = model.getProjectList().get(0);
        CompleteCommand completeCommand = new CompleteCommand(projectToDelete.getProjectName(), VALID_DATE);

        String expectedMessage = String.format(CompleteCommand.MESSAGE_COMPLETE_PROJECT_SUCCESS, projectToDelete);

        ModelManager expectedModel = new ModelManager(model.getPocketProject(), new UserPrefs());
        expectedModel.completeProject(projectToDelete, VALID_DATE);
        expectedModel.commitPocketProject();

        assertCommandSuccess(completeCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidName_throwsCommandException() {
        CompleteCommand completeCommand = new CompleteCommand(
                TypicalProjectNames.NON_EXISTENT_PROJECT_NAME, VALID_DATE);

        assertCommandFailure(completeCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_PROJECT_NAME);
    }


    @Test
    public void executeUndoRedo_validName_success() throws Exception {
        Project projectToDelete = model.getProjectList().get(0);
        CompleteCommand completeCommand = new CompleteCommand(projectToDelete.getProjectName(), VALID_DATE);
        Model expectedModel = new ModelManager(model.getPocketProject(), new UserPrefs());
        expectedModel.completeProject(projectToDelete, VALID_DATE);
        expectedModel.commitPocketProject();

        // complete -> first project completed
        completeCommand.execute(model, commandHistory);

        // undo -> reverts pocket project back to previous state and filtered project list to show all projects
        expectedModel.undoPocketProject();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first project completed again
        expectedModel.redoPocketProject();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        CompleteCommand completeCommand = new CompleteCommand(
                TypicalProjectNames.NON_EXISTENT_PROJECT_NAME, VALID_DATE);

        // execution failed -> pocket project state not added into model
        assertCommandFailure(completeCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_PROJECT_NAME);

        // single pocket project state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }


    @Test
    public void equals() {
        CompleteCommand completeAliceCommand = new CompleteCommand(
                TypicalProjects.PROJECT_ALICE.getProjectName(), VALID_DATE);
        CompleteCommand completeBensonCommand = new CompleteCommand(
                TypicalProjects.PROJECT_BENSON.getProjectName(), VALID_DATE);

        // same object -> returns true
        assertTrue(completeAliceCommand.equals(completeAliceCommand));

        // same values -> returns true
        CompleteCommand completeAliceCommandCopy = new CompleteCommand(
                TypicalProjects.PROJECT_ALICE.getProjectName(), VALID_DATE);
        assertTrue(completeAliceCommand.equals(completeAliceCommandCopy));

        // different types -> returns false
        assertFalse(completeAliceCommand.equals(1));

        // null -> returns false
        assertFalse(completeAliceCommand.equals(null));

        // different project -> returns false
        assertFalse(completeAliceCommand.equals(completeBensonCommand));
    }

}
