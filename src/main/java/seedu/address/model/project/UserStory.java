package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

/**
 * Represents a user story stored in pocket project.
 */
public class UserStory {

    private UserStoryImportance importance;
    private UserStoryUser user;
    private UserStoryFunction function;
    private UserStoryReason reason;
    private UserStoryStatus status;

    /**
     * Constructor for a user story
     */
    public UserStory(UserStoryImportance importance, UserStoryUser user, UserStoryFunction function,
                     UserStoryReason reason) {
        requireNonNull(importance);
        requireNonNull(user);
        requireNonNull(function);
        requireNonNull(reason);

        this.importance = importance;
        this.user = user;
        this.function = function;
        this.reason = reason;
        this.status = new UserStoryStatus();
    }

    /**
     * Constructor for a user story including its status
     */
    public UserStory(UserStoryImportance importance, UserStoryUser user, UserStoryFunction function,
                     UserStoryReason reason, UserStoryStatus status) {
        this(importance, user, function, reason);
        this.status = status;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else { //check all the fields for equality
            return other instanceof UserStory
                    && importance.equals(((UserStory) other).importance)
                    && user.equals(((UserStory) other).user)
                    && function.equals(((UserStory) other).function)
                    && reason.equals(((UserStory) other).reason)
                    && status.equals(((UserStory) other).status);
        }
    }

    public String getUserStoryImportance() {
        return importance.getImportance();
    }

    public String getUserStoryReason() {
        return reason.getReason();
    }

    public String getUserStoryFunction() {
        return function.getFunction();
    }

    public String getUserStoryUser() {
        return user.getUser();
    }

    public String getUserStoryStatus() {
        return status.getStatus();
    }

    /**
     * Comparison between user stories. If two user stories are idential in all the string fields, then
     * it should be considered as the same story even if the importance level or status is different.
     * @param story
     * @return
     */
    public boolean isSameUserStory(UserStory story) {
        if (story == this) {
            return true;
        } else { //check the string fields
            return user.equals(story.user)
                    && function.equals(story.function)
                    && reason.equals(story.reason);
        }
    }

    /**
     * Returns a clone of this user story.
     */
    public UserStory clone() {
        return new UserStory(this.importance.clone(), this.user.clone(), this.function.clone(),
                this.reason.clone(), this.status.clone());
    }

    public boolean isHigherImportance(UserStory other) {
        return this.importance.isHigherImportance(other.importance);
    }

    /**
     * Utility method to sort user story by the user's name. If the two strings are equal then this method returns false
     */
    public boolean isUserLexicographicallySmaller(UserStory other) {
        if (getUserStoryUser().compareTo(other.getUserStoryUser()) < 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User: ")
                .append(user.getUser())
                .append(" Function: ")
                .append(function.getFunction())
                .append(" Reason: ")
                .append(reason.getReason())
                .append(" Importance: ")
                .append(importance.getImportance());
        return builder.toString();
    }

    /**
     * Checks if the user story has the valid format by checking the relevant fields
     */
    public static boolean isValidUserStory(UserStory story) {
        return UserStoryUser.isValidUserStoryUser(story.user.getUser())
                && UserStoryImportance.isValidImportanceLevel(story.importance.getImportance())
                && UserStoryFunction.isValidUserStoryFunction(story.function.getFunction())
                && UserStoryStatus.isValidStoryStatus(story.status.getStatus());
    }
}
