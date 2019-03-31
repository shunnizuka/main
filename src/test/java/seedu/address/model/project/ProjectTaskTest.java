package seedu.address.model.project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ProjectTaskTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ProjectTask(null));
        Assert.assertThrows(NullPointerException.class, () -> new ProjectTask(null, null));
    }

    @Test
    public void constructor_invalidProjectTask_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName(""))); // empty string
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName(" "))); // spaces only

        // invalid tasks with task status constructor
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName(""), new ProjectTaskStatus(""))); // empty string
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName(""), new ProjectTaskStatus(" "))); // spaces only

    }

    @Test
    public void isValidTask() {
        // invalid task null status
        Assert.assertThrows(NullPointerException.class, () ->
                new ProjectTask(new ProjectTaskName("a"), new ProjectTaskStatus(null)));

        //invalid task status strings
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName("a"), new ProjectTaskStatus(""))); // empty string
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName("a"), new ProjectTaskStatus(" "))); // spaces only
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName("a"), new ProjectTaskStatus("complet"))); // wrong status string
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new ProjectTask(new ProjectTaskName("a"), new ProjectTaskStatus("ongoin"))); // wrong status string

        // valid task
        assertTrue(ProjectTask.isValidTask(new ProjectTask(new ProjectTaskName("a")))); // only task name constructor

        assertTrue(ProjectTask.isValidTask(new ProjectTask(
                new ProjectTaskName("a"),
                new ProjectTaskStatus("ongoing")))); // include task status constructor
        assertTrue(ProjectTask.isValidTask(new ProjectTask(
                new ProjectTaskName(" Create a task"),
                new ProjectTaskStatus("ongoing")))); // task name with whitespace at start of string
        assertTrue(ProjectTask.isValidTask(new ProjectTask(
                new ProjectTaskName("Create a task "),
                new ProjectTaskStatus("complete")))); // task name with trailing whitespace
        assertTrue(ProjectTask.isValidTask(new ProjectTask(
                new ProjectTaskName("New task at pp/v1.1"),
                new ProjectTaskStatus("complete")))); // task name with non-alphanumeric characters
        assertTrue(ProjectTask.isValidTask(new ProjectTask(
                new ProjectTaskName("New task at pp/v1.1"),
                new ProjectTaskStatus("CoMpLeTe")))); // case insensitive task status
        assertTrue(ProjectTask.isValidTask(new ProjectTask(
                new ProjectTaskName("New task at pp/v1.1"),
                new ProjectTaskStatus("OnGoInG")))); // case insensitive task status
    }
}
