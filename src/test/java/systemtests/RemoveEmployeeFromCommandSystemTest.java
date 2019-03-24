package systemtests;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.RemoveEmployeeFromCommand;
import seedu.address.logic.commands.RemoveFromCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.project.Project;
import seedu.address.testutil.TypicalProjects;

public class RemoveEmployeeFromCommandSystemTest extends PocketProjectSystemTest {

    @Test
    public void removeEmployeeFrom() {

        Model model = getProjectModel();

        /* ------------------------ Perform addto operations on the shown unfiltered list -------------------------- */

        /* Case: removes an employee from a project in the non-empty pocket project, command with leading spaces
         * and trailing
         * spaces
         */

        Project targetProject = model.getProjectWithName(TypicalProjects.PROJECT_ALICE.getProjectName());
        int validEmployeeIndex = model.getProjectWithName(targetProject.getProjectName()).getEmployees().size();
        Employee targetEmployee = model.getProjectWithName(targetProject.getProjectName()).getEmployees()
                .get(validEmployeeIndex - 1);
        String command = RemoveEmployeeFromCommand.COMMAND_WORD + " " + targetProject.getProjectName() + " "
                + RemoveEmployeeFromCommand.REMOVE_EMPLOYEE_KEYWORD + " " + validEmployeeIndex;

        assertCommandSuccess(command, targetProject, targetEmployee);

        /* Case: undo removing employee from Project Alice */
        command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedResultMessage);

        /* Case: redo removing from the list -> removed again */
        command = RedoCommand.COMMAND_WORD;
        model.removeEmployeeFrom(targetProject, targetEmployee);
        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedResultMessage);
        /* ----------------------------------- Perform invalid add operations ------------------------------------- */
        /* Case: add a non existing employee from a project -> rejected */
        command = RemoveEmployeeFromCommand.COMMAND_WORD + " " + targetProject.getProjectName() + " "
                + RemoveEmployeeFromCommand.REMOVE_EMPLOYEE_KEYWORD + " " + 99;
        assertCommandFailure(command, MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);

        /* Case: missing command word -> rejected */
        command = targetProject.getProjectName() + " " + RemoveEmployeeFromCommand.REMOVE_EMPLOYEE_KEYWORD + " " + 1;
        assertCommandFailure(command, String.format(Messages.MESSAGE_UNKNOWN_COMMAND));

        /* Case: missing project name -> rejected */
        command = RemoveEmployeeFromCommand.COMMAND_WORD + " " + RemoveEmployeeFromCommand.REMOVE_EMPLOYEE_KEYWORD
                + " " + 1;
        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromCommand.MESSAGE_USAGE));

        /* Case: missing employee keyword -> rejected */
        command = RemoveEmployeeFromCommand.COMMAND_WORD + " " + targetProject.getProjectName() + " " + 1;
        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromCommand.MESSAGE_USAGE));

        /* Case: missing employee index -> rejected */
        command = RemoveEmployeeFromCommand.COMMAND_WORD + " " + targetProject.getProjectName() + " "
                + RemoveEmployeeFromCommand.REMOVE_EMPLOYEE_KEYWORD;
        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromCommand.MESSAGE_USAGE));
    }

    /**
     * Executes the {@code AddEmployeeToCommand} that adds {@code targetEmployee}
     * to {@code targetProject} in the model and asserts that the,<br>
     * 1. Command box displays an empty string.<br>
     * 2. Command box has the default style class.<br>
     * 3. Result display box displays the success message of executing {@code AddEmployeeToCommand} with the details of
     * {@code toAdd}.<br>
     * 4. {@code Storage} equal to the corresponding components in
     * the current model added with {@code targetEmployee} and {@code targetProject}.<br>
     * 5. Browser url and selected card remain unchanged.<br>
     * 6. Status bar's sync status changes.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code PocketProjectSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see PocketProjectSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */

    private void assertCommandSuccess(String command, Project targetProject, Employee targetEmployee) {
        Model expectedModel = getProjectModel();
        expectedModel.removeEmployeeFrom(targetProject, targetEmployee);
        String expectedResultMessage = String.format(RemoveEmployeeFromCommand.MESSAGE_REMOVE_EMPLOYEE_SUCCESS,
                targetEmployee, targetProject.getProjectName());

        assertCommandSuccess(command, expectedModel, expectedResultMessage);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Project, Employee)} except asserts that
     * the,<br>
     * 1. Result display box displays {@code expectedResultMessage}.<br>
     * 2. {@code Storage} and {@code EmployeeListPanel} equal to the corresponding components in
     * {@code expectedModel}.<br>
     * @see RemoveEmployeeFromCommandSystemTest#assertCommandSuccess(String, Project, Employee)
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
     * 4. {@code Storage} and {@code EmployeeListPanel} remain unchanged.<br>
     * 5. Browser url, selected card and status bar remain unchanged.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code PocketProjectSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see PocketProjectSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */

    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getEmployeeModel();
        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
