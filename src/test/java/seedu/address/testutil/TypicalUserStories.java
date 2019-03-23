package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.PocketProject;
import seedu.address.model.project.UserStory;

/**
 * A utility class containing a list of {@code UserStory} objects to be used in tests.
 */
public class TypicalUserStories {

    public static final UserStory USER_STORY_TYPICAL_MANAGER = new UserStoryBuilder()
            .withPriority("3")
            .withUser("typical manager")
            .withFunction("be able to add projects")
            .withBenefit("keep a record of my projects")
            .build();

    public static final UserStory USER_STORY_MANY_PROJECTS_MANAGER = new UserStoryBuilder()
            .withPriority("3")
            .withUser("manager in charge of many projects")
            .withFunction("be able to see a list of my projects")
            .withBenefit("better track my projects progress")
            .build();

    public static final UserStory USER_STORY_MANY_EMPLOYEES_MANAGER = new UserStoryBuilder()
            .withPriority("3")
            .withUser("manager in charge of many employees")
            .withFunction("be able to see a list of my employees")
            .withBenefit("better track my employees progress")
            .build();

    public static final UserStory USER_STORY_FIRST_MANAGER = new UserStoryBuilder()
            .withPriority("2")
            .withUser("Software engineering project manager")
            .withFunction("See a recommended list of employees based on the skill requirement of a project")
            .withBenefit("Easily assign employees with the required skills to the project")
            .build();

    public static final UserStory USER_STORY_SECOND_MANAGER = new UserStoryBuilder()
            .withPriority("2")
            .withUser("Software engineering project manager")
            .withFunction("See total number of projects listed as completed and ongoing")
            .withBenefit("Keep track of how many projects have been successfully completed and the number of " +
                    "ongoing projects at a glance")
            .build();

    private TypicalUserStories() {} //prevent instantiation

}
