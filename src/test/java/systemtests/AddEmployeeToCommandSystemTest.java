package systemtests;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddEmployeeToCommand;
import seedu.address.logic.commands.AddToCommand;
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
}
