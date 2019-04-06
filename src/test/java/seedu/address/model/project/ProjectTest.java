package seedu.address.model.project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CLIENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_BOB;
import static seedu.address.testutil.TypicalProjects.PROJECT_ALICE;
import static seedu.address.testutil.TypicalProjects.PROJECT_CARL;
import static seedu.address.testutil.TypicalUserStories.USER_STORY_TYPICAL_MANAGER;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.ProjectBuilder;

public class ProjectTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    //TODO: Make it throw unsupported operation exception

    /*
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Project project = new ProjectBuilder().build();
        thrown.expect(UnsupportedOperationException.class);
    }
    */

    @Test
    public void addMilestone() {
        //milestone is properly added
        Project p = PROJECT_CARL;
        Milestone m1 = new Milestone("1", "11/11/2011");
        p.addMilestone(m1);
        assertTrue(p.getMilestones().equals(Arrays.asList(m1)));

        //milestone is sorted after being added
        Milestone m2 = new Milestone("2", "09/11/2011");
        p.addMilestone(m2);
        assertTrue(p.getMilestones().equals(Arrays.asList(m2, m1)));
        assertFalse(p.getMilestones().equals(Arrays.asList(m1, m2)));

        //adding more
        Milestone m3 = new Milestone("3", "10/11/2011");
        p.addMilestone(m3);
        assertTrue(p.getMilestones().equals(Arrays.asList(m2, m3, m1)));
    }

    @Test
    public void isSameProject() {
        // same object -> returns true
        assertTrue(PROJECT_ALICE.isSameProject(PROJECT_ALICE));

        // null -> returns false
        assertFalse(PROJECT_ALICE.isSameProject(null));

        //TODO: Fill up more test cases to compare project equality
    }

    @Test
    public void equals() {
        // same values -> returns true
        Project projectAliceCopy = new ProjectBuilder(PROJECT_ALICE).build();
        assertTrue(PROJECT_ALICE.equals(projectAliceCopy));

        // same object -> returns true
        assertTrue(PROJECT_ALICE.equals(PROJECT_ALICE));

        // null -> returns false
        assertFalse(PROJECT_ALICE.equals(null));

        // different type -> returns false
        assertFalse(PROJECT_ALICE.equals(5));

        // different project -> returns false
        assertFalse(PROJECT_ALICE.equals(PROJECT_CARL));

        // different name -> returns false
        Project editedAlice = new ProjectBuilder(PROJECT_ALICE).withProjectName(VALID_PROJECT_NAME_BOB).build();
        assertFalse(PROJECT_ALICE.equals(editedAlice));

        // different client -> returns false
        editedAlice = new ProjectBuilder(PROJECT_ALICE).withClient(VALID_CLIENT_BOB).build();
        assertFalse(PROJECT_ALICE.equals(editedAlice));

        // different date -> returns false
        editedAlice = new ProjectBuilder(PROJECT_ALICE).withDeadline(VALID_DEADLINE_BOB).build();
        assertFalse(PROJECT_ALICE.equals(editedAlice));

        //different userstory -> returns false
        editedAlice = new ProjectBuilder(PROJECT_ALICE).withUserStories(Arrays.asList(USER_STORY_TYPICAL_MANAGER))
                .build();
        assertFalse(PROJECT_ALICE.equals(editedAlice));
    }
}
