package seedu.address.logic.commands;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLIENT_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_ALICE_HEY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT_USER_STORY;
import static seedu.address.testutil.TypicalIndexes.INDEX_INVALID_PROJECT_USER_STORY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROJECT_USER_STORY;
import static seedu.address.testutil.TypicalProjects.getTypicalPocketProjectWithProjects;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PocketProject;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.UserStory;
import seedu.address.model.project.UserStoryUser;
import seedu.address.testutil.EditUserStoryDescriptorBuilder;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.TypicalProjectNames;
import seedu.address.testutil.TypicalProjects;
import seedu.address.testutil.TypicalUserStories;
import seedu.address.testutil.TypicalIndexes;

public class EditProjectUserStoryCommandTest {

    private Model model = new ModelManager(getTypicalPocketProjectWithProjects(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    //default builder
    private ProjectBuilder defaultProjBuilder = new ProjectBuilder().withProjectName(VALID_PROJECT_NAME_ALICE_HEY)
            .withClient(VALID_CLIENT_ALICE).withDeadline(VALID_DEADLINE_ALICE).withDescrption(
                    "An application for Alice software hello")
            .withUserStories(Arrays.asList(TypicalUserStories.USER_STORY_FIRST_MANAGER));

    @Test
    public void execute_allFieldsSpecified_success() {

        Project editedProject = defaultProjBuilder
                .withUserStories(Arrays.asList(TypicalUserStories.USER_STORY_FIRST_MANAGER)).build();

        EditProjectUserStoryCommand.EditUserStoryDescriptor descriptor = new EditUserStoryDescriptorBuilder(
                TypicalUserStories.USER_STORY_FIRST_MANAGER).build();
        EditProjectUserStoryCommand editUserStoryCommand = new EditProjectUserStoryCommand(
                new ProjectName(VALID_PROJECT_NAME_ALICE_HEY) ,
                INDEX_FIRST_PROJECT_USER_STORY, descriptor);

        Model expectedModel = new ModelManager(new PocketProject(model.getPocketProject()), new UserPrefs());

        expectedModel.setProject(model.getFilteredProjectList().get(0), editedProject);
        expectedModel.commitPocketProject();

        String expectedMessage = String.format(EditProjectUserStoryCommand.MESSAGE_EDIT_STORY_SUCCESS ,
                TypicalUserStories.USER_STORY_FIRST_MANAGER);

        assertCommandSuccess(editUserStoryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldSpecified_success() {
        Project editedProject = defaultProjBuilder.build();

        EditProjectUserStoryCommand.EditUserStoryDescriptor descriptor = new EditUserStoryDescriptorBuilder(
                TypicalUserStories.USER_STORY_FIRST_MANAGER).build();
        EditProjectUserStoryCommand editUserStoryCommand = new EditProjectUserStoryCommand(
                new ProjectName(VALID_PROJECT_NAME_ALICE_HEY) ,
                INDEX_FIRST_PROJECT_USER_STORY, descriptor);

        Model expectedModel = new ModelManager(new PocketProject(model.getPocketProject()), new UserPrefs());
       // expectedModel.setProject(model.getFilteredProjectList().get(0), editedProject);
        expectedModel.commitPocketProject();

        String expectedMessage = String.format(EditProjectUserStoryCommand.MESSAGE_EDIT_STORY_SUCCESS ,
                TypicalUserStories.USER_STORY_FIRST_MANAGER);

        assertCommandSuccess(editUserStoryCommand, model, commandHistory, expectedMessage, expectedModel);



    }

    @Test
    public void execute_noFieldSpecified_success() {

        EditProjectUserStoryCommand editProjectUserStoryCommand = new EditProjectUserStoryCommand(
                TypicalProjectNames.TYPICAL_PROJECT_NAME_1, INDEX_FIRST_PROJECT_USER_STORY,
                new EditProjectUserStoryCommand.EditUserStoryDescriptor());

        String expectedMessage = String.format(EditProjectUserStoryCommand.MESSAGE_EDIT_STORY_SUCCESS,
                TypicalUserStories.USER_STORY_TYPICAL_MANAGER);

        Model expectedModel = new ModelManager(new PocketProject(model.getPocketProject()), new UserPrefs());
        expectedModel.commitPocketProject();

        assertCommandSuccess(editProjectUserStoryCommand, model, commandHistory, expectedMessage, expectedModel);

    }

    @Test
    public void execute_duplicateUserStory_failure() {

        Project firstProject = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        UserStory firstStory = firstProject.getUserStories().get(INDEX_FIRST_PROJECT_USER_STORY.getZeroBased());

        EditProjectUserStoryCommand.EditUserStoryDescriptor descriptor =
                new EditUserStoryDescriptorBuilder(firstStory).build();
        EditProjectUserStoryCommand editProjectUserStoryCommand = new EditProjectUserStoryCommand(firstProject
                .getProjectName(), INDEX_SECOND_PROJECT_USER_STORY, descriptor);

        assertCommandFailure(editProjectUserStoryCommand, model, commandHistory,
                EditProjectUserStoryCommand.MESSAGE_DUPLICATE_USER_STORY);
    }

    @Test
    public void execute_invalidProjectName_failure() {
        EditProjectUserStoryCommand.EditUserStoryDescriptor descriptor = new EditUserStoryDescriptorBuilder()
                .withUser("some user").build();
        EditProjectUserStoryCommand editProjectUserStoryCommand = new EditProjectUserStoryCommand(
                new ProjectName("wheee"), INDEX_FIRST_PROJECT_USER_STORY, descriptor);

        assertCommandFailure(editProjectUserStoryCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_PROJECT_NAME);
    }

    @Test
    public void execute_invalidUserStoryIndex_failure() {
        EditProjectUserStoryCommand.EditUserStoryDescriptor descriptor = new EditUserStoryDescriptorBuilder()
                .withUser("some other user").build();
        EditProjectUserStoryCommand editProjectUserStoryCommand = new EditProjectUserStoryCommand(
                new ProjectName(VALID_PROJECT_NAME_ALICE_HEY), INDEX_INVALID_PROJECT_USER_STORY, descriptor);

        assertCommandFailure(editProjectUserStoryCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_USERSTORY_DISPLAYED_INDEX);
    }

/*
    @Test
    public void executeUndoRedo_validFields_success() throws CommandException {

        Project editedProject = defaultProjBuilder.withMilestones(Arrays.asList(
                TYPICAL_MILESTONE_COMPLETED_UG, TYPICAL_MILESTONE_END)).build();

        EditProjectMilestoneCommand.EditMilestoneDescriptor descriptor = new EditMilestoneDescriptorBuilder(
                TYPICAL_MILESTONE_COMPLETED_UG).build();
        EditProjectMilestoneCommand editMilestoneCommand = new EditProjectMilestoneCommand(
                new ProjectName(VALID_PROJECT_NAME_ALICE_HEY) , INDEX_FIRST_PROJECT_MILESTONE, descriptor);

        Model expectedModel = new ModelManager(new PocketProject(model.getPocketProject()), new UserPrefs());
        expectedModel.setProject(model.getFilteredProjectList().get(0), editedProject);
        expectedModel.commitPocketProject();

        editMilestoneCommand.execute(model, commandHistory);

        expectedModel.undoPocketProject();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        expectedModel.redoPocketProject();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);

    }

    @Test
    public void executeUndoRedo_invalidFields_failure() {

        EditProjectMilestoneCommand.EditMilestoneDescriptor descriptor = new EditMilestoneDescriptorBuilder()
                .withMilestoneDesc("Project starts").build();
        EditProjectMilestoneCommand editProjectMilestoneCommand = new EditProjectMilestoneCommand(
                new ProjectName("wheee"), INDEX_FIRST_PROJECT_MILESTONE, descriptor);

        assertCommandFailure(editProjectMilestoneCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_PROJECT_NAME);

        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }
*/
    @Test
    public void equals() {
        final EditProjectUserStoryCommand standardCommand = new EditProjectUserStoryCommand(
                new ProjectName(VALID_PROJECT_NAME_ALICE_HEY), INDEX_FIRST_PROJECT_USER_STORY,
                new EditUserStoryDescriptorBuilder().build());

        // same values -> returns true
        EditProjectUserStoryCommand.EditUserStoryDescriptor copyDescriptor =
                new EditProjectUserStoryCommand.EditUserStoryDescriptor();
        EditProjectUserStoryCommand commandWithSameValues = new EditProjectUserStoryCommand(
                new ProjectName(VALID_PROJECT_NAME_ALICE_HEY), INDEX_FIRST_PROJECT_USER_STORY, copyDescriptor);

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditProjectUserStoryCommand(
                new ProjectName(VALID_PROJECT_NAME_ALICE_HEY),
                INDEX_SECOND_PROJECT_USER_STORY,new EditUserStoryDescriptorBuilder().withUser("user").withFunction("function")
                .withReason("reason").withImportance("3").build() )));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditProjectUserStoryCommand(
                new ProjectName(VALID_PROJECT_NAME_ALICE_HEY),
                INDEX_SECOND_PROJECT_USER_STORY, new EditUserStoryDescriptorBuilder().withUser("user").withFunction("function")
                .withReason("reason").withImportance("3").build())));
    }
}
