package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * represents the user of a user story
 */
public class UserStoryUser {

    public static final String DEFAULT_USER = "Typical user";
    public static final String PREFIX_USER = "as a";
    public static final String MESSAGE_CONSTRAINTS = "User story should have a valid user.";

    /**
     * Only accept alphanumberic values as possible name values to be consistent with Client class.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private String user;

    /**
     * Constructor for creating a user in a user story.
     * @param user
     */
    public UserStoryUser(String user) {
        requireNonNull(user);
        checkArgument(isValidUserStoryUser(user), MESSAGE_CONSTRAINTS);
        this.user = user;
    }

    public String getUser() {
        return this.user;
    }

    /**
     * Returns true if a given string is a valid importance level.
     */
    public static boolean isValidUserStoryUser(String input) {
        return input.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserStoryUser // instanceof handles nulls
                && (user.equals(((UserStoryUser) other).getUser()))); // state check
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }

    /**
     * Returns a clone of this user story user.
     */
    public UserStoryUser clone() {
        return new UserStoryUser(this.user);
    }

}
