package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.UserStory;
import seedu.address.model.project.UserStoryImportance;
import seedu.address.model.project.UserStoryReason;
import seedu.address.model.project.UserStoryFunction;
import seedu.address.model.project.UserStoryUser;

/**
 * Jackson-friendly version of {@link UserStory}.
 */
class JsonAdaptedUserStory {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "User story's %s field is missing!";

    private final String importance;
    private final String user;
    private final String function;
    private final String reason;

    /**
     * Constructs a {@code JsonAdaptedUserStory} with the given user story details.
     */
    @JsonCreator
    public JsonAdaptedUserStory(@JsonProperty("importance") String importance, @JsonProperty("user") String user,
                                @JsonProperty("function") String function, @JsonProperty("reason") String reason) {
        this.importance = importance;
        this.user = user;
        this.function = function;
        this.reason = reason;
    }

    /**
     * Converts a given {@code User Story} into this class for Jackson use.
     */
    public JsonAdaptedUserStory(UserStory source) {
        this.importance = source.getUserStoryImportance().getImportance();
        this.user = source.getUserStoryUser().getUser();
        this.function = source.getUserStoryFunction().getFunction();
        this.reason = source.getUserStoryReason().getReason();
    }

    /**
     * Converts this Jackson-friendly adapted user story object into the model's {@code UserStory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted user story.
     */
    public UserStory toModelType() throws IllegalValueException {

        if (importance == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    UserStoryImportance.class.getSimpleName()));
        }
        if (!UserStoryImportance.isValidImportanceLevel(importance)) {
            throw new IllegalValueException(UserStoryImportance.MESSAGE_CONSTRAINTS);
        }
        final UserStoryImportance modelUserStoryImportance = new UserStoryImportance(importance);

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
        if (!UserStoryFunction.isValdUserStoryFunction(function)) {
            throw new IllegalValueException(UserStoryFunction.MESSAGE_CONSTRAINTS);
        }
        final UserStoryFunction modelUserStoryFunction = new UserStoryFunction(function);

        if (reason == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    UserStoryReason.class.getSimpleName()));
        }
        final UserStoryReason modelUserStoryReason = new UserStoryReason(reason);

        return new UserStory(modelUserStoryImportance, modelUserStoryUser, modelUserStoryFunction, modelUserStoryReason);
    }

}
