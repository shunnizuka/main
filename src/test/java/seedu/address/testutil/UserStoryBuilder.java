package seedu.address.testutil;

import seedu.address.model.project.UserStory;
import seedu.address.model.project.UserStoryBenefit;
import seedu.address.model.project.UserStoryFunction;
import seedu.address.model.project.UserStoryPriority;
import seedu.address.model.project.UserStoryUser;

/**
 * A utility class to help with building UserStory objects.
 */
public class UserStoryBuilder {

    public static final String DEFAULT_PRIORITY = "3";
    public static final String DEFAULT_USER = "Software engineering project manager";
    public static final String DEFAULT_FUNCTION = "Be able to add/delete projects in the app";
    public static final String DEFAULT_BENEFIT = "Keep track of any new projects and remove unnecessary entries";

    private UserStoryPriority priority;
    private UserStoryUser user;
    private UserStoryFunction function;
    private UserStoryBenefit benefit;

    public UserStoryBuilder() {
        priority = new UserStoryPriority(DEFAULT_PRIORITY);
        user = new UserStoryUser(DEFAULT_USER);
        function = new UserStoryFunction(DEFAULT_FUNCTION);
        benefit = new UserStoryBenefit(DEFAULT_BENEFIT);
    }

    /**
     * Initializes the UserStoryBuilder with the data of {@code userStoryToCopy}.
     */
    public UserStoryBuilder(UserStory userStoryToCopy) {
        priority = userStoryToCopy.getUserStoryPriority();
        user = userStoryToCopy.getUserStoryUser();
        function = userStoryToCopy.getUserStoryFunction();
        benefit = userStoryToCopy.getUserStoryBenefit();
    }

    /**
     * Sets the {@code UserStoryPriority} of the {@code UserStory} that we are building.
     */
    public UserStoryBuilder withPriority(String priority) {
        this.priority = new UserStoryPriority(priority);
        return this;
    }

    /**
     * Sets the {@code UserStoryUser} of the {@code UserStory} that we are building.
     */
    public UserStoryBuilder withUser(String user) {
        this.user = new UserStoryUser(user);
        return this;
    }

    /**
     * Sets the {@code UserStoryFunction} of the {@code UserStory} that we are building.
     */
    public UserStoryBuilder withFunction(String function) {
        this.function = new UserStoryFunction(function);
        return this;
    }

    /**
     * Sets the {@code UserStoryBenefit} of the {@code UserStory} that we are building.
     */
    public UserStoryBuilder withBenefit(String benefit) {
        this.benefit = new UserStoryBenefit(benefit);
        return this;
    }

    public UserStory build() {
        return new UserStory(priority, user, function, benefit);
    }

}
