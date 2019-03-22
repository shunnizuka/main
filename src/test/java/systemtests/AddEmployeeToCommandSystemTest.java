package systemtests;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddEmployeeToCommand;
import seedu.address.logic.commands.AddToCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.project.Project;
import seedu.address.testutil.TypicalProjects;

import org.junit.Test;

public class AddEmployeeToCommandSystemTest extends PocketProjectSystemTest {

    @Test
    public void addTo() {

        Model model = getModel();

        /* ------------------------ Perform addto operations on the shown unfiltered list ----------------------------- */

        /* Case: add an employee to a project in the non-empty pocket project, command with leading spaces and trailing
         * spaces
         */

        Project targetProject = model.getProjectWithName(TypicalProjects.PROJECT_ALICE.getProjectName());
        System.out.println(targetProject);
        int validIndex = model.getFilteredEmployeeList().size() - 1;
        Employee targetEmployee = model.getFilteredEmployeeList()
            .get(Index.fromOneBased(model.getFilteredEmployeeList().size() - 1).getZeroBased());
        String command = AddToCommand.COMMAND_WORD + " " + targetProject.getProjectName() + " "
            + AddEmployeeToCommand.ADD_EMPLOYEE_KEYWORD + " " + validIndex;

        assertCommandSuccess(command, targetProject, targetEmployee);
        System.out.println(targetProject);

        /* Case: undo adding employee to Project Alice */
        command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedResultMessage);
        System.out.println(targetProject);

        /* Case: redo adding Amy to the list -> Amy added again */
        command = RedoCommand.COMMAND_WORD;
        model.addEmployeeTo(targetProject, targetEmployee);
        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedResultMessage);

        /* ----------------------------------- Perform invalid add operations --------------------------------------- */
        /* Case: add a duplicate employee to a project -> rejected */
        command = AddToCommand.COMMAND_WORD + " " + targetProject.getProjectName() + " "
                + AddEmployeeToCommand.ADD_EMPLOYEE_KEYWORD + " " + validIndex;
        assertCommandFailure(command, AddEmployeeToCommand.MESSAGE_DUPLICATE_PROJ_EMPLOYEE);
    }

    private void assertCommandSuccess(String command,Project targetProject, Employee targetEmployee) {
        Model expectedModel = getModel();
        expectedModel.addEmployeeTo(targetProject, targetEmployee);
        String expectedResultMessage = String.format(AddEmployeeToCommand.MESSAGE_ADDTOPROJECT_EMPLOYEE_SUCCESS,
            targetEmployee, targetProject.getProjectName());

        assertCommandSuccess(command, expectedModel, expectedResultMessage);
    }

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
        Model expectedModel = getModel();
        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
