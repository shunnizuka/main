package seedu.address.model.project;

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

}
