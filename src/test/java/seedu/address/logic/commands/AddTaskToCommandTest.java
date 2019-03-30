package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddTaskToCommand.MESSAGE_COMMAND_EXECUTED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;

import org.junit.Test;
import seedu.address.logic.CommandHistory;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
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
        TaskName name = new TaskName("Do something");
        Task task = new Task(name);
        assertCommandFailure(new AddTaskToCommand(targetProject.getProjectName(), task), model, commandHistory,
                MESSAGE_COMMAND_EXECUTED);
    }
}
