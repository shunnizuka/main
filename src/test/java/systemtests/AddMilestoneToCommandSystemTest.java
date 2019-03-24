package systemtests;

import seedu.address.logic.commands.AddMilestoneToCommand;
import seedu.address.logic.commands.AddToCommand;
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
        Milestone milestone = TypicalMilestones.TYPICAL_MILESTONE_START;
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
        assertCommandSuccess(ProjectUtil.getAddMilestoneToCommand(toAdd), toAdd);
    }






}

