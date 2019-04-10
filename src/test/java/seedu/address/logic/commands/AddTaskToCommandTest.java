package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.AddTaskToCommand.MESSAGE_ADD_PROJECT_TASK_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT_MILESTONE;
import static seedu.address.testutil.TypicalMilestones.TYPICAL_MILESTONE_START;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.ProjectTask;
import seedu.address.model.project.ProjectTaskDescription;
import seedu.address.testutil.TestUtil;
import seedu.address.testutil.TypicalProjects;

public class AddTaskToCommandTest {

    private Model model = new ModelManager(TestUtil.typicalPocketProject(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validProjectTaskNameValidIndex_success() {
        Project targetProject = model.getProjectWithName(TypicalProjects.PROJECT_ALICE.getProjectName());
        ProjectName projectName = targetProject.getProjectName();
        ProjectTask task = new ProjectTask(new ProjectTaskDescription("Do something"));
        AddTaskToCommand addTaskToCommand = new AddTaskToCommand(projectName, task,
                INDEX_FIRST_PROJECT_MILESTONE);
        String expectedMessage = String.format(MESSAGE_ADD_PROJECT_TASK_SUCCESS, task,
                INDEX_FIRST_PROJECT_MILESTONE.getOneBased(), projectName);

        ModelManager expectedModel = new ModelManager(model.getPocketProject(), new UserPrefs());
        expectedModel.addProjectTaskTo(targetProject, TYPICAL_MILESTONE_START, task);
        expectedModel.commitPocketProject();

        assertCommandSuccess(addTaskToCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidProjectName_throwsCommandException() {
        AddTaskToCommand addTaskToCommand = new AddTaskToCommand(new ProjectName("INVALID"),
                new ProjectTask(new ProjectTaskDescription("Something happened!")), INDEX_FIRST_PROJECT_MILESTONE);
        assertCommandFailure(addTaskToCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_PROJECT_NAME);
    }

    @Test
    public void execute_invalidIndexValidProjectName_throwsCommandException() {
        Project targetProject = model.getProjectWithName(TypicalProjects.PROJECT_ALICE.getProjectName());
        Index outOfBoundIndex = Index.fromOneBased(targetProject.getMilestones().size() + 1);
        AddTaskToCommand addTaskToCommand = new AddTaskToCommand(targetProject.getProjectName(),
                new ProjectTask(new ProjectTaskDescription("Something happened!")), outOfBoundIndex);

        assertCommandFailure(addTaskToCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_MILESTONE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ProjectTask newTask = new ProjectTask(new ProjectTaskDescription("Testing"));
        final AddTaskToCommand standardCommand = new AddTaskToCommand(TypicalProjects.PROJECT_ALICE.getProjectName(),
                newTask, Index.fromOneBased(1));

        // same values -> returns true
        AddTaskToCommand commandWithSameValues = new AddTaskToCommand(TypicalProjects.PROJECT_ALICE.getProjectName(),
                newTask, Index.fromOneBased(1));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddTaskToCommand(TypicalProjects.PROJECT_ALICE.getProjectName(),
                newTask, Index.fromOneBased(2))));

        // different project -> returns false
        assertFalse(standardCommand.equals(new AddTaskToCommand(TypicalProjects.PROJECT_BENSON.getProjectName(),
                newTask, Index.fromOneBased(1))));
    }
}
