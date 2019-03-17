package seedu.address.logic.commands;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Employee;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.testutil.TestUtil;
import seedu.address.testutil.TypicalProjects;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
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
        expectedModel.addEmployeeTo(targetProject, employeeToAdd);
        expectedModel.commitAddressBook();

        assertCommandSuccess(addEmployeeToCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidProjName_throwsCommandException() {
        AddEmployeeToCommand addEmployeeToCommand = new AddEmployeeToCommand(Index.fromOneBased(1),
                new ProjectName("INVALID"));
        assertCommandFailure(addEmployeeToCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_PROJECT_NAME);
    }

    @Test
    public void execute_invalidIndexValidProjectName_throwsCommandException() {
        Project targetProject = model.getProjectWithName(TypicalProjects.PROJECT_ALICE.getProjectName());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEmployeeList().size() + 1);
        AddEmployeeToCommand addEmployeeToCommand = new AddEmployeeToCommand(outOfBoundIndex,
                targetProject.getProjectName());

        assertCommandFailure(addEmployeeToCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
    }
}
