package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.testutil.TestUtil;
import seedu.address.testutil.TypicalProjects;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;

/**
 * Contains unit tests for
 * {@code RemoveMilestoneFromCommand}.
 */
public class AddMilestoneToCommandTest {

    private Model model = new ModelManager(TestUtil.typicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_invalidProjectName_throwsCommandException() {
        AddMilestoneToCommand addMilestoneToCommand = new AddMilestoneToCommand(new ProjectName("INVALID"), new
            Milestone("Updated UG", "23/06/2019"));
        assertCommandFailure(addMilestoneToCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_PROJECT_NAME);
    }

    @Test
    public void execute_invalidMilestoneValidProjectName_throwsCommandException() {
        Project targetProject = model.getProjectWithName(TypicalProjects.PROJECT_ALICE.getProjectName());
        Milestone ms = new Milestone("Done and dusted.", "233/05/2020");
        AddMilestoneToCommand addMilestoneToCommand = new AddMilestoneToCommand(targetProject.getProjectName(), ms);

        assertCommandFailure(addMilestoneToCommand, model, commandHistory, Milestone.MESSAGE_CONSTRAINTS);
    }
    @Test
    public void equals() {
        AddMilestoneToCommand addMilestoneToCommandOne = new AddMilestoneToCommand
            (TypicalProjects.PROJECT_ALICE.getProjectName(), new Milestone("Completed", "22/05/2019"));
        AddMilestoneToCommand addMilestoneToCommandTwo = new AddMilestoneToCommand
                (TypicalProjects.PROJECT_BENSON.getProjectName(), new Milestone("Completed",
                    "30/11/2019"));

        // same object -> returns true
        assertTrue(addMilestoneToCommandOne.equals(addMilestoneToCommandOne));

        // same values -> returns true
        AddMilestoneToCommand addMilestoneToCommandOneCopy = new AddMilestoneToCommand
            ((TypicalProjects.PROJECT_ALICE.getProjectName()), new Milestone("Completed", "22/05/2019"));
        assertTrue(addMilestoneToCommandOne.equals(addMilestoneToCommandOneCopy));

        // different types -> returns false
        assertFalse(addMilestoneToCommandOne.equals(1));

        // null -> returns false
        assertFalse(addMilestoneToCommandOne.equals(null));

        // different projects -> returns false
        assertFalse(addMilestoneToCommandOne.equals(addMilestoneToCommandTwo));

        // different dates -> returns false
        AddMilestoneToCommand addMilestoneToCommandThree = new AddMilestoneToCommand
                (TypicalProjects.PROJECT_ALICE.getProjectName(), new Milestone("Completed",
                    "23/05/2019"));
        assertFalse(addMilestoneToCommandOne.equals(addMilestoneToCommandThree));

        //different description -> returns false
        AddMilestoneToCommand addMilestoneToCommandFour = new AddMilestoneToCommand
                (TypicalProjects.PROJECT_ALICE.getProjectName(), new Milestone("Completed all",
                    "22/05/2019"));
        assertFalse(addMilestoneToCommandOne.equals(addMilestoneToCommandFour));
    }

}
