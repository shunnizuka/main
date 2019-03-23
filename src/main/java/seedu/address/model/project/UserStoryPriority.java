package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * represents the priority level of a user story
 */
public class UserStoryPriority {

    public static final String MESSAGE_CONSTRAINTS =
            "User story should have a priority of 1 to 3. 3 being the most important and 1 being the least important.";

    /**
     * Checks if the string is a number between 1 and 3.
     */
    public static final String VALIDATION_REGEX = "[1-3]";

    private Integer priority;

    public UserStoryPriority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriorityLevel(priority), MESSAGE_CONSTRAINTS);
        this.priority = Integer.parseInt(priority);
    }

    public Integer getPriority() {
        return this.priority;
    }

    /**
     * Returns true if a given string is a valid priority level.
     */
    public static boolean isValidPriorityLevel(String input) {
        return input.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserStoryPriority // instanceof handles nulls
                && (priority.equals(((UserStoryPriority) other).getPriority()))); // state check
    }

    @Override
    public int hashCode() {
        return priority.hashCode();
    }

    /**
     * Returns a clone of this user story priority.
     */
    public UserStoryPriority clone() {
        return new UserStoryPriority(this.priority.toString());
    }
}
