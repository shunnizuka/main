package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

/**
 * represents the function of a user story
 */
public class UserStoryFunction {

    public static final String DEFAULT_FUNCTION = "Typical function";
    public static final String PREAMBLE_FUNCTION = "i can ";
    public static final String MESSAGE_CONSTRAINTS = "User story should have a valid function.";

    private String function;

    /**
     * Constructor for creating a function in a user story.
     * @param function
     */
    public UserStoryFunction(String function) {
        requireNonNull(function);
        this.function = function;
    }

    public String getFunction() {
        return this.function;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserStoryFunction // instanceof handles nulls
                && (function.equals(((UserStoryFunction) other).getFunction()))); // state check
    }

    @Override
    public int hashCode() {
        return function.hashCode();
    }

    /**
     * Returns a clone of this user story function.
     */
    public UserStoryFunction clone() {
        return new UserStoryFunction(this.function);
    }
}
