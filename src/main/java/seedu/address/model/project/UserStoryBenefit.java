package seedu.address.model.project;

public class UserStoryBenefit {

    public static final String DEFAULT_BENEFIT = "";

    private String benefit;

    /**
     * Constructor for creating a benefit of a user story.
     * @param benefit
     */
    private UserStoryBenefit(String benefit) {
        this.benefit = benefit;
    }

    /**
     * Alternative constructor to handle instances where the function is sufficient to represent a user story
     * and no explicit benefit is required.
     */
    private UserStoryBenefit() {
        this.benefit = DEFAULT_BENEFIT;
    }

    public String getBenefit() {
        return this.benefit;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserStoryBenefit // instanceof handles nulls
                && (benefit.equals(((UserStoryBenefit) other).getBenefit()))); // state check
    }

    @Override
    public int hashCode() {
        return benefit.hashCode();
    }

    /**
     * Returns a clone of this user story benefit.
     */
    public UserStoryBenefit clone() {
        return new UserStoryBenefit(this.benefit);
    }
}
