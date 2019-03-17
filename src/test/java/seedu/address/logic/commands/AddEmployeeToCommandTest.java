package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Employee;
import seedu.address.model.project.Project;
import seedu.address.testutil.TestUtil;
import seedu.address.testutil.TypicalProjects;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code AddEmployeeToCommand}.
 */
public class AddEmployeeToCommandTest {

    private Model model = new ModelManager(TestUtil.typicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Project targetProject = model.getProjectWithName(TypicalProjects.PROJECT_ALICE.getProjectName());
        Employee employeeToAdd = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        AddEmployeeToCommand addEmployeeToCommand = new AddEmployeeToCommand(INDEX_FIRST_EMPLOYEE,
            targetProject.getProjectName());

        String expectedMessage = String.format(AddEmployeeToCommand.MESSAGE_ADDTOPROJECT_EMPLOYEE_SUCCESS,
                employeeToAdd, targetProject.getProjectName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addEmployeeTo(targetProject, targetEmployee);
        expectedModel.commitAddressBook();

        assertCommandSuccess(removeEmployeeFromCommand, model, commandHistory, expectedMessage, expectedModel);


    }

}
