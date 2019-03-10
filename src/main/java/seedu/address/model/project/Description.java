package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
//import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Description of the project. A summary of what the project is about
 */

public class Description {

    public static final String MESSAGE_CONSTRAINTS =
        "Description should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String description;

    /**
     * Empty Constructor for when description is not added to the project yet
     */
    public Description() {
        this.description = "";
    }

    /**
     * Constructor for when adding description to the project
     */
    public Description(String desc) {
        requireNonNull(desc);
        //checkArgument(isValidDescription(desc), MESSAGE_CONSTRAINTS);
        this.description = desc;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidDescription(String input) {
        return input.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this// short circuit if same object
            || (other instanceof Description // instanceof handles nulls
            && description.equals(((Description) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }




}
