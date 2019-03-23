package systemtests;

import static seedu.address.testutil.TypicalProjects.PROJECT_XAVIER;
import static seedu.address.testutil.TypicalProjects.PROJECT_ZULU;

import seedu.address.logic.commands.AddProjectCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.model.Model;
import seedu.address.model.project.Client;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.testutil.ProjectUtil;
import seedu.address.testutil.TypicalProjects;

import org.junit.Test;

public class AddProjectCommandSystemTest extends PocketProjectSystemTest {

    @Test
    public void addProject() {

        Model model = getModel();
        String command;

        /* ------------------------ Perform add operations on the shown unfiltered list ----------------------------- */

        /* Case: add a project with only name, client and deadline to a non-empty pocket project, command with leading
         * spaces and trailing spaces
         * -> added
         */

        Project toAdd = PROJECT_ZULU;
        assertCommandSuccess(toAdd);

        /* Case: undo adding Project Zulu to the list -> Project Zulu deleted */
        command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedResultMessage);

        /* Case: redo adding Project Zulu to the list -> Project Zulu added again */
        command = RedoCommand.COMMAND_WORD;
        model.addProject(toAdd);
        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedResultMessage);

        /* Case: add a employee with all fields same as another employee in the pocket project except name -> added */
        toAdd = new Project(new ProjectName("Zululu"), new Client("Shunnizuka"),
                new Deadline("04/09/2023"));
        assertCommandSuccess(toAdd);

        /* Case: add to empty pocket project -> added */
        deleteAllProjects();
        assertCommandSuccess(toAdd);

        /* -------------------------- Perform add operation on the shown filtered list ------------------------------ */

        /* Case: filters the employee list before adding -> added */
        showProjectsWithName(TypicalProjects.KEYWORD_MATCHING_BENSON);
        assertCommandSuccess(PROJECT_XAVIER);

        /* ------------------------ Perform add operation while a project card is selected ------------------------- */

        /* Case: selects first card in the project list, add a project -> added, card selection remains unchanged */
        //TODO
    }

    /**
     * Executes the {@code AddProjectCommand} that adds {@code toAdd} to the model and asserts that the,<br>
     * 1. Command box displays an empty string.<br>
     * 2. Command box has the default style class.<br>
     * 3. Result display box displays the success message of executing {@code AddProjectCommand} with the details of
     * {@code toAdd}.<br>
     * 4. {@code Storage} and {@code ProjectListPanel} equal to the corresponding components in
     * the current model added with {@code toAdd}.<br>
     * 5. Browser url and selected card remain unchanged.<br>
     * 6. Status bar's sync status changes.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code PocketProjectSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see PocketProjectSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(Project toAdd) {
        assertCommandSuccess(ProjectUtil.getAddProjectCommand(toAdd), toAdd);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(Employee)}. Executes {@code command}
     * instead.
     * @see AddProjectCommandSystemTest#assertCommandSuccess(Project)
     */
    private void assertCommandSuccess(String command, Project toAdd) {
        Model expectedModel = getModel();
        expectedModel.addProject(toAdd);
        String expectedResultMessage = String.format(AddProjectCommand.MESSAGE_ADD_PROJECT_SUCCESS, toAdd);

        assertCommandSuccess(command, expectedModel, expectedResultMessage);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Project)} except asserts that
     * the,<br>
     * 1. Result display box displays {@code expectedResultMessage}.<br>
     * 2. {@code Storage} and {@code ProjectListPanel} equal to the corresponding components in
     * {@code expectedModel}.<br>
     * @see AddProjectCommandSystemTest#assertCommandSuccess(String, Project)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchangedExceptSyncStatus();
    }

    /**
     * Executes {@code command} and asserts that the,<br>
     * 1. Command box displays {@code command}.<br>
     * 2. Command box has the error style class.<br>
     * 3. Result display box displays {@code expectedResultMessage}.<br>
     * 4. {@code Storage} and {@code ProjectListPanel} remain unchanged.<br>
     * 5. Browser url, selected card and status bar remain unchanged.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code PocketProjectSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see PocketProjectSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();
        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
