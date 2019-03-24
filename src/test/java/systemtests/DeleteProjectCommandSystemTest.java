package systemtests;

import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.DeleteProjectCommand.MESSAGE_DELETE_PROJECT_SUCCESS;
import static seedu.address.testutil.TestUtil.getProject;
import static seedu.address.testutil.TestUtil.getProjectLastIndex;
import static seedu.address.testutil.TestUtil.getProjectMidIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalProjects.KEYWORD_MATCHING_BENSON;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteProjectCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

public class DeleteProjectCommandSystemTest extends PocketProjectSystemTest {

    private static final String MESSAGE_INVALID_DELETE_COMMAND_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteProjectCommand.MESSAGE_USAGE);

    @Test
    public void delete() {
        /* ----------------- Performing delete operation while an unfiltered list is being shown -------------------- */

        /* Case: delete the first project in the list, command with leading spaces and trailing spaces -> deleted */
        Model expectedModel = getProjectModel();
        String command = "     " + DeleteProjectCommand.COMMAND_WORD + "      " + " project "
                + INDEX_FIRST_PROJECT.getOneBased() + "       ";
        Project deletedProject = removeProject(expectedModel, INDEX_FIRST_PROJECT);
        String expectedResultMessage = String.format(MESSAGE_DELETE_PROJECT_SUCCESS, deletedProject);
        assertCommandSuccess(command, expectedModel, expectedResultMessage);

        /* Case: delete the last project in the list -> deleted */
        Model modelBeforeDeletingLast = getProjectModel();
        Index lastProjectIndex = getProjectLastIndex(modelBeforeDeletingLast);
        assertCommandSuccess(lastProjectIndex);

        /* Case: undo deleting the last project in the list -> last project restored */
        command = UndoCommand.COMMAND_WORD;
        expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, modelBeforeDeletingLast, expectedResultMessage);

        /* Case: redo deleting the last project in the list -> last project deleted again */
        command = RedoCommand.COMMAND_WORD;
        removeProject(modelBeforeDeletingLast, lastProjectIndex);
        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, modelBeforeDeletingLast, expectedResultMessage);

        /* Case: delete the middle project in the list -> deleted */
        Index middleProjectIndex = getProjectMidIndex(getProjectModel());
        assertCommandSuccess(middleProjectIndex);

        /* ------------------ Performing delete operation while a filtered list is being shown ---------------------- */

        /* Case: filtered project list, delete index within bounds of address book and project list -> deleted */
        showProjectsWithName(KEYWORD_MATCHING_BENSON);
        Index index = INDEX_FIRST_PROJECT;
        assertTrue(index.getZeroBased() < getProjectModel().getFilteredProjectList().size());
        assertCommandSuccess(index);

        /* Case: filtered project list, delete index within bounds of address book but out of bounds of project list
         * -> rejected
         */
        showProjectsWithName(KEYWORD_MATCHING_BENSON);
        int invalidIndex = getProjectModel().getPocketProject().getProjectList().size();
        command = DeleteProjectCommand.COMMAND_WORD + " project " + invalidIndex;
        assertCommandFailure(command, MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);


        /* --------------------------------- Performing invalid delete operation ------------------------------------ */

        /* Case: invalid index (0) -> rejected */
        command = DeleteProjectCommand.COMMAND_WORD + " project 0";
        assertCommandFailure(command, MESSAGE_INVALID_DELETE_COMMAND_FORMAT);

        /* Case: invalid index (-1) -> rejected */
        command = DeleteProjectCommand.COMMAND_WORD + " project -1";
        assertCommandFailure(command, MESSAGE_INVALID_DELETE_COMMAND_FORMAT);

        /* Case: invalid index (size + 1) -> rejected */
        Index outOfBoundsIndex = Index.fromOneBased(
                getProjectModel().getPocketProject().getProjectList().size() + 1);
        command = DeleteProjectCommand.COMMAND_WORD + " project " + outOfBoundsIndex.getOneBased();
        assertCommandFailure(command, MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    /**
     * Removes the {@code Project} at the specified {@code index} in {@code model}'s pocket project.
     * @return the removed project
     */
    private Project removeProject(Model model, Index index) {
        Project targetProject = getProject(model, index);
        model.deleteProject(targetProject);
        return targetProject;
    }

    /**
     * Deletes the project at {@code toDelete} by
     * creating a default {@code DeleteProjectCommand} using {@code toDelete} and
     * performs the same verification as {@code assertCommandSuccess(String, Model, String)}.
     * @see DeleteProjectCommandSystemTest#assertCommandSuccess(String, Model, String)
     */
    private void assertCommandSuccess(Index toDelete) {
        Model expectedModel = getProjectModel();
        Project deletedProject = removeProject(expectedModel, toDelete);
        String expectedResultMessage = String.format(MESSAGE_DELETE_PROJECT_SUCCESS, deletedProject);
        assertCommandSuccess(
                DeleteProjectCommand.COMMAND_WORD + " project " + toDelete.getOneBased(),
                expectedModel, expectedResultMessage);
    }

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays an empty string.<br>
     * 2. Asserts that the result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the browser url and selected card remains unchanged.<br>
     * 4. Asserts that the status bar's sync status changes.<br>
     * 5. Asserts that the command box has the default style class.<br>
     * Verifications 1 and 2 are performed by
     * {@code PocketProjectSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.
     * @see PocketProjectSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        assertCommandSuccess(command, expectedModel, expectedResultMessage, null);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Model, String)} except that the browser url
     * and selected card are expected to update accordingly depending on the card at {@code expectedSelectedCardIndex}.
     * @see DeleteProjectCommandSystemTest#assertCommandSuccess(String, Model, String)
     * @see PocketProjectSystemTest#assertSelectedCardChanged(Index)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage,
                                      Index expectedSelectedCardIndex) {
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);

        if (expectedSelectedCardIndex != null) {
            assertSelectedCardChanged(expectedSelectedCardIndex);
        } else {
            assertSelectedCardUnchanged();
        }

        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchangedExceptSyncStatus();
    }

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays {@code command}.<br>
     * 2. Asserts that result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the browser url, selected card and status bar remain unchanged.<br>
     * 4. Asserts that the command box has the error style.<br>
     * Verifications 1 and 2 are performed by
     * {@code PocketProjectSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see PocketProjectSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getProjectModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
