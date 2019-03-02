package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Client of the project. Basically who we are doing this project for.
 */

public class Client {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String client;

    /**
     * Constructor for ProjectName.
     */
    public Client(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.client = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String input) {
        return input.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return client;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Client // instanceof handles nulls
                && client.equals(((Client) other).client)); // state check
    }

    @Override
    public int hashCode() {
        return client.hashCode();
    }
}

