package systemtests;

import seedu.address.logic.commands.AddMilestoneToCommand;
import seedu.address.model.Model;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectUtil;
import seedu.address.testutil.TypicalMilestones;
import seedu.address.testutil.TypicalProjects;

import org.junit.Test;

public class AddMilestoneToCommandSystemTest extends PocketProjectSystemTest {

    @Test
    public void addMilestoneTo() {
        Model model = getProjectModel();

        /* ------------------------ Perform addto operations on the shown unfiltered list -------------------------- */

        /* Case: add a milestone to a project in the non-empty pocket project, command with leading spaces and trailing
         * spaces
         */

        Project targetProject = model.getProjectWithName(TypicalProjects.PROJECT_ALICE.getProjectName());
        Milestone milestone = TypicalMilestones.TYPICAL_MILESTONE_COMPLETED_UG;
        assertCommandSuccess(targetProject, milestone);

    }

    /**
     * Executes the {@code AddMilestoneToCommand} that adds {@code milestone} to the model and asserts that the,<br>
     * 1. Command box displays an empty string.<br>
     * 2. Command box has the default style class.<br>
     * 3. Result display box displays the success message of executing {@code AddMilestoneToCommand} with the details of
     * {@code milestone}.<br>
     * 4. {@code Storage} equal to the corresponding components in
     * the current model added with {@code milestone}.<br>
     * 5. Browser url and selected card remain unchanged.<br>
     * 6. Status bar's sync status changes.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code PocketProjectSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see PocketProjectSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */

    private void assertCommandSuccess(Project targetProject, Milestone milestone) {
        assertCommandSuccess(ProjectUtil.getAddMilestoneToCommand(targetProject, milestone), targetProject, milestone);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(Project, Milestone)}. Executes {@code command}
     * instead. @see AddMilestoneToCommandSystemTest#assertCommandSuccess(Project, Milestone)
     */

    private void assertCommandSuccess(String command, Project targetProject, Milestone milestone) {
        Model expectedModel = getProjectModel();
        expectedModel.addMilestoneTo(targetProject, milestone);
        String expectedResultMessage = String.format(AddMilestoneToCommand.MESSAGE_ADD_MILESTONE_SUCCESS,
                milestone, targetProject.getProjectName());

        assertCommandSuccess(command, expectedModel, expectedResultMessage);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Project, Milestone)} except asserts that
     * the,<br>
     * 1. Result display box displays {@code expectedResultMessage}.<br>
     * 2. {@code Storage} equal to the corresponding components in
     * {@code expectedModel}.<br>
     * @see AddMilestoneToCommandSystemTest#assertCommandSuccess(String, Project, Milestone)
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
     * 4. {@code Storage} remain unchanged.<br>
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

