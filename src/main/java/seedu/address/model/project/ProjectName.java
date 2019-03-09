package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Name of the project in the Pocket Project.
 */

public class ProjectName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the project name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String projectName;

    /**
     * Constructor for ProjectName.
     */
    public ProjectName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.projectName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String input) {
        return input.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return projectName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectName // instanceof handles nulls
                && projectName.equals(((ProjectName) other).projectName)); // state check
    }

    @Override
    public int hashCode() {
        return projectName.hashCode();
    }
}
