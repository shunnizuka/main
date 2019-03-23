package seedu.address.model.project;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user story stored in pocket project.
 */
public class UserStory {

    UserStoryPriority priority;
    UserStoryUser user;
    UserStoryFunction function;
    UserStoryBenefit benefit;

    /**
     * Constructor for a user story
     */
    public UserStory(UserStoryPriority priority, UserStoryUser user, UserStoryFunction function,
                     UserStoryBenefit benefit) {
        this.priority = priority;
        this.user = user;
        this.function = function;
        this.benefit = benefit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        //check all the fields for equality
        else return other instanceof UserStory
                &&  priority.equals(((UserStory) other).priority)
                && user.equals(((UserStory) other).user)
                && function.equals(((UserStory) other).function)
                && benefit.equals(((UserStory) other).benefit);
    }

    public UserStoryPriority getUserStoryPriority() {
        return priority;
    }

    public UserStoryBenefit getUserStoryBenefit() {
        return benefit;
    }

    public UserStoryFunction getUserStoryFunction() {
        return function;
    }

    public UserStoryUser getUserStoryUser() {
        return user;
    }

    /**
     * Comparison between user stories. If two user stories are idential in all the string fields, then
     * it should be considered as the same story even if the priority level is different.
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
                && benefit.equals(story.benefit);
    }

    /**
     * Returns a clone of this user story.
     */
    public UserStory clone() {
        return new UserStory(this.priority.clone(), this.user.clone(), this.function.clone(),
                this.benefit.clone());
    }

    public boolean isHigherPriority(UserStory other) {
        return this.priority.isHigherPriority(other.priority);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User: ")
                .append(getUserStoryUser())
                .append(" Function: ")
                .append(getUserStoryFunction())
                .append(" Benefit: ")
                .append(getUserStoryBenefit());
        return builder.toString();
    }
}
