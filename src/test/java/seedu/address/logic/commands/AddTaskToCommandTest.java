package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.AddTaskToCommand.MESSAGE_ADD_TASK_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;

import org.junit.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.Task;
import seedu.address.model.project.TaskName;
import seedu.address.testutil.TestUtil;
import seedu.address.testutil.TypicalProjects;

public class AddTaskToCommandTest {

    private Model model = new ModelManager(TestUtil.typicalPocketProject(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute() {
        Project targetProject = model.getProjectWithName(TypicalProjects.PROJECT_ALICE.getProjectName());
        ProjectName projectName = targetProject.getProjectName();
        TaskName taskName = new TaskName("Do something");
        Task task = new Task(taskName);
        Index validIndex = Index.fromOneBased(targetProject.getMilestones().size());
        assertCommandFailure(new AddTaskToCommand(projectName, task, validIndex), model, new CommandHistory(),
                String.format(MESSAGE_ADD_TASK_SUCCESS, task, projectName));
    }

    @Test
    public void equals() {
        Task newTask = new Task(new TaskName("Testing"));
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
