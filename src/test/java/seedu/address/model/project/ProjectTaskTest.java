package seedu.address.model.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import static seedu.address.testutil.TypicalProjectTasks.PROJECT_TASK_COMPLETED;
import static seedu.address.testutil.TypicalProjectTasks.PROJECT_TASK_DO_SOMETHING;

import org.junit.Test;

import seedu.address.model.util.PocketProjectDate;
import seedu.address.testutil.Assert;

public class ProjectTaskTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ProjectTask(null));
        Assert.assertThrows(NullPointerException.class, () -> new ProjectTask(null, null, null));
    }

    @Test
    public void constructor_invalidProjectTask_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName(""))); // empty string
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName(" "))); // spaces only

        // invalid tasks with task status constructor
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName(""), new Status(""), new PocketProjectDate())); // empty string
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName(""), new Status(" "), new PocketProjectDate())); // spaces only
    }

    @Test
    public void isValidProjectTask() {
        // invalid task null status
        Assert.assertThrows(NullPointerException.class, () ->
                new ProjectTask(new ProjectTaskName("a"), new Status(null), new PocketProjectDate()));

        //invalid task status strings
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName("a"), new Status(""), new PocketProjectDate())); // empty string
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName("a"), new Status(" "), new PocketProjectDate())); // spaces only
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName("a"), new Status("complet"),
                new PocketProjectDate())); // wrong status string
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName("a"), new Status("ongoin"),
                new PocketProjectDate())); // wrong status string

        // invalid completion date
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName("a"), new Status("ongoin"),
                new PocketProjectDate("222/01/2019"))); // wrong date format

        // valid task
        assertTrue(ProjectTask.isValidTask(new ProjectTask(new ProjectTaskName("a")))); // only task name constructor

        assertTrue(ProjectTask.isValidTask(new ProjectTask(
                new ProjectTaskName("a"), new Status("ongoing"),
                new PocketProjectDate()))); // include task status and completion date constructor
        assertTrue(ProjectTask.isValidTask(new ProjectTask(
                new ProjectTaskName(" Create a task"), new Status("ongoing"),
                new PocketProjectDate()))); // task name with whitespace at start of string
        assertTrue(ProjectTask.isValidTask(new ProjectTask(
                new ProjectTaskName("Create a task "), new Status("complete"),
                new PocketProjectDate()))); // task name with trailing whitespace
        assertTrue(ProjectTask.isValidTask(new ProjectTask(
                new ProjectTaskName("New task at pp/v1.1"), new Status("complete"),
                new PocketProjectDate()))); // task name with non-alphanumeric characters
        assertTrue(ProjectTask.isValidTask(new ProjectTask(
                new ProjectTaskName("New task at pp/v1.1"), new Status("CoMpLeTe"),
                new PocketProjectDate()))); // case insensitive task status
        assertTrue(ProjectTask.isValidTask(new ProjectTask(
                new ProjectTaskName("New task at pp/v1.1"), new Status("OnGoInG"),
                new PocketProjectDate()))); // case insensitive task status
    }

    @Test
    public void isSameProjectTaskName() {
        // equals
        ProjectTaskName validProjectTaskName = new ProjectTaskName("Valid");
        ProjectTask validProjectTask = new ProjectTask(new ProjectTaskName("Valid"), new Status("ongoing"),
                new PocketProjectDate());
        assertEquals(validProjectTask.getTaskName(), validProjectTaskName.getTaskName());

        // not equals, case-sensitive
        ProjectTask invalidProjectTask = new ProjectTask(new ProjectTaskName("valid"),
                new Status("ongoing"), new PocketProjectDate());
        assertNotEquals(invalidProjectTask.getTaskName(), validProjectTaskName.getTaskName());
    }

    @Test
    public void isSameStatus() {
        // same string -> equals
        Status status = new Status("ongoing");
        ProjectTask validProjectTask = new ProjectTask(new ProjectTaskName("Valid"), new Status("ongoing"),
                new PocketProjectDate());
        assertEquals(validProjectTask.getTaskStatus(), status.getStatus());

        // status case insensitive -> equals
        ProjectTask caseProjectTask = new ProjectTask(new ProjectTaskName("Valid"), new Status("Ongoing"),
                new PocketProjectDate());
        assertEquals(caseProjectTask.getTaskStatus(), status.getStatus());

        // different statuses -> not equals
        ProjectTask invalidProjectTask = new ProjectTask(new ProjectTaskName("Valid"), new Status("Complete"),
                new PocketProjectDate());
        assertNotEquals(invalidProjectTask.getTaskStatus(), status.getStatus());
    }

    @Test
    public void isSameTask() {
        // same object -> return true
        assertTrue(PROJECT_TASK_DO_SOMETHING.isSameTask(PROJECT_TASK_DO_SOMETHING));

        // null -> return false
        assertFalse(PROJECT_TASK_DO_SOMETHING.isSameTask(null));

        // different project task -> returns false
        assertFalse(PROJECT_TASK_DO_SOMETHING.isSameTask(PROJECT_TASK_COMPLETED));
    }

    @Test
    public void equals() {
        // same values -> return true
        ProjectTask typicalCopy = PROJECT_TASK_DO_SOMETHING.clone();
        assertTrue(typicalCopy.equals(PROJECT_TASK_DO_SOMETHING));

        // same object -> return true
        assertTrue(PROJECT_TASK_DO_SOMETHING.equals(PROJECT_TASK_DO_SOMETHING));

        // null -> return false
        assertFalse(PROJECT_TASK_DO_SOMETHING.equals(null));

        // different type -> returns false
        assertFalse(PROJECT_TASK_DO_SOMETHING.equals(5));

        // different project task -> returns false
        assertFalse(PROJECT_TASK_DO_SOMETHING.equals(PROJECT_TASK_COMPLETED));

        // different completion date
        ProjectTask differentDateTask =
                new ProjectTask(new ProjectTaskName("Completed task"), new Status("complete"),
                new PocketProjectDate("01/01/2019"));
        assertFalse(PROJECT_TASK_COMPLETED.equals(differentDateTask));
    }
}
