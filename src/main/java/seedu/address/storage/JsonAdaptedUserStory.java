package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.UserStory;
import seedu.address.model.project.UserStoryBenefit;
import seedu.address.model.project.UserStoryFunction;
import seedu.address.model.project.UserStoryPriority;
import seedu.address.model.project.UserStoryUser;

/**
 * Jackson-friendly version of {@link UserStory}.
 */
class JsonAdaptedUserStory {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "User story's %s field is missing!";

    private final String priority;
    private final String user;
    private final String function;
    private final String benefit;

    /**
     * Constructs a {@code JsonAdaptedUserStory} with the given user story details.
     */
    @JsonCreator
    public JsonAdaptedUserStory(@JsonProperty("priority") String priority, @JsonProperty("user") String user,
                                @JsonProperty("function") String function, @JsonProperty("benefit") String benefit) {
        this.priority = priority;
        this.user = user;
        this.function = function;
        this.benefit = benefit;
    }

    /**
     * Converts a given {@code User Story} into this class for Jackson use.
     */
    public JsonAdaptedUserStory(UserStory source) {
        priority = source.getUserStoryPriority().getPriority().toString();
        user = source.getUserStoryUser().getUser();
        function = source.getUserStoryFunction().getFunction();
        benefit = source.getUserStoryBenefit().getBenefit();
    }

    /**
     * Converts this Jackson-friendly adapted user story object into the model's {@code UserStory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted user story.
     */
    public UserStory toModelType() throws IllegalValueException {

        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    UserStoryPriority.class.getSimpleName()));
        }
        if (!UserStoryPriority.isValidPriorityLevel(priority)) {
            throw new IllegalValueException(UserStoryPriority.MESSAGE_CONSTRAINTS);
        }
        final UserStoryPriority modelUserStoryPriority = new UserStoryPriority(priority);

        if (user == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    UserStoryUser.class.getSimpleName()));
        }
        if (!UserStoryUser.isValidUserStoryUser(user)) {
            throw new IllegalValueException(UserStoryUser.MESSAGE_CONSTRAINTS);
        }
        final UserStoryUser modelUserStoryUser = new UserStoryUser(user);

        if (function == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    UserStoryFunction.class.getSimpleName()));
        }
        final UserStoryFunction modelUserStoryFunction = new UserStoryFunction(function);

        if (benefit == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    UserStoryBenefit.class.getSimpleName()));
        }
        final UserStoryBenefit modelUserStoryBenefit = new UserStoryBenefit(benefit);

        return new UserStory(modelUserStoryPriority, modelUserStoryUser, modelUserStoryFunction, modelUserStoryBenefit);
    }

}
