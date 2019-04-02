package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Contains information about the user story status.
 */

public class UserStoryStatus {


    public static final String MESSAGE_CONSTRAINTS = "User stories can only be set to ongoing, complete or on hold";
    public static final String STATUS_COMPLETE = "complete";
    public static final String STATUS_ONGOING = "ongoing";
    public static final String STATUS_ON_HOLD = "on hold";

    private String storyStatus;

    /**
     * Default constructor.
     */
    public UserStoryStatus() {
        this.storyStatus = STATUS_ONGOING;
    }

    /**
     * Constructor for TaskStatus specifying status.
     */
    public UserStoryStatus(String status) {
        requireNonNull(status);
        checkArgument(isValidStoryStatus(status), MESSAGE_CONSTRAINTS);
        this.storyStatus = status;
    }

    public String getStatus() {
        return this.storyStatus;
    }

    /**
     * Returns true if given string is a valid user story status
     */
    public static boolean isValidStoryStatus(String status) {
        return status.equalsIgnoreCase(STATUS_COMPLETE)
                || status.equalsIgnoreCase(STATUS_ON_HOLD)
                || status.equalsIgnoreCase(STATUS_ONGOING);
    }

    /**
     * Returns a clone of this TaskStatus object.
     */
    public UserStoryStatus clone() {
        return new UserStoryStatus(this.storyStatus);
    }

    @Override
    public String toString() {
        return this.storyStatus;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserStoryStatus // instanceof handles nulls
                && storyStatus.equals(((UserStoryStatus) other).storyStatus)); // state check
    }

    @Override
    public int hashCode() {
        return storyStatus.hashCode();
    }
}

