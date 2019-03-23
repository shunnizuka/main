package seedu.address.model.project;

/**
 * Represents a user story stored in pocket project.
 */
public class UserStory {

    UserStoryImportance importance;
    UserStoryUser user;
    UserStoryFunction function;
    UserStoryReason reason;

    /**
     * Constructor for a user story
     */
    public UserStory(UserStoryImportance importance, UserStoryUser user, UserStoryFunction function,
                     UserStoryReason reason) {
        this.importance = importance;
        this.user = user;
        this.function = function;
        this.reason = reason;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        //check all the fields for equality
        else return other instanceof UserStory
                &&  importance.equals(((UserStory) other).importance)
                && user.equals(((UserStory) other).user)
                && function.equals(((UserStory) other).function)
                && reason.equals(((UserStory) other).reason);
    }

    public UserStoryImportance getUserStoryImportance() {
        return importance;
    }

    public UserStoryReason getUserStoryReason() {
        return reason;
    }

    public UserStoryFunction getUserStoryFunction() {
        return function;
    }

    public UserStoryUser getUserStoryUser() {
        return user;
    }

    /**
     * Comparison between user stories. If two user stories are idential in all the string fields, then
     * it should be considered as the same story even if the importance level is different.
     * @param story
     * @return
     */
    public boolean isSameUserStory(UserStory story) {
        if (story == this) {
            return true;
        }
        //check the string fields
        else return user.equals(story.user)
                && function.equals(story.function)
                && reason.equals(story.reason);
    }

    /**
     * Returns a clone of this user story.
     */
    public UserStory clone() {
        return new UserStory(this.importance.clone(), this.user.clone(), this.function.clone(),
                this.reason.clone());
    }

    public boolean isHigherImportance(UserStory other) {
        return this.importance.isHigherImportance(other.importance);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User: ")
                .append(getUserStoryUser())
                .append(" Function: ")
                .append(getUserStoryFunction())
                .append(" Reason: ")
                .append(getUserStoryReason())
                .append(" Importance: ")
                .append(getUserStoryImportance());
        return builder.toString();
    }
}
