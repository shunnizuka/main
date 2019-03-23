package seedu.address.testutil;

import seedu.address.model.project.UserStory;
import seedu.address.model.project.UserStoryImportance;
import seedu.address.model.project.UserStoryReason;
import seedu.address.model.project.UserStoryFunction;
import seedu.address.model.project.UserStoryUser;

/**
 * A utility class to help with building UserStory objects.
 */
public class UserStoryBuilder {

    public static final String DEFAULT_IMPORTANCE = "3";
    public static final String DEFAULT_USER = "Software engineering project manager";
    public static final String DEFAULT_FUNCTION = "Be able to add/delete projects in the app";
    public static final String DEFAULT_REASON = "Keep track of any new projects and remove unnecessary entries";

    private UserStoryImportance importance;
    private UserStoryUser user;
    private UserStoryFunction function;
    private UserStoryReason reason;

    public UserStoryBuilder() {
        importance = new UserStoryImportance(DEFAULT_IMPORTANCE);
        user = new UserStoryUser(DEFAULT_USER);
        function = new UserStoryFunction(DEFAULT_FUNCTION);
        reason = new UserStoryReason(DEFAULT_REASON);
    }

    /**
     * Initializes the UserStoryBuilder with the data of {@code userStoryToCopy}.
     */
    public UserStoryBuilder(UserStory userStoryToCopy) {
        importance = userStoryToCopy.getUserStoryImportance();
        user = userStoryToCopy.getUserStoryUser();
        function = userStoryToCopy.getUserStoryFunction();
        reason = userStoryToCopy.getUserStoryReason();
    }

    /**
     * Sets the {@code UserStoryImportance} of the {@code UserStory} that we are building.
     */
    public UserStoryBuilder withImportance(String importance) {
        this.importance = new UserStoryImportance(importance);
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
     * Sets the {@code UserStoryReason} of the {@code UserStory} that we are building.
     */
    public UserStoryBuilder withReason(String reason) {
        this.reason = new UserStoryReason(reason);
        return this;
    }

    public UserStory build() {
        return new UserStory(importance, user, function, reason);
    }

}
