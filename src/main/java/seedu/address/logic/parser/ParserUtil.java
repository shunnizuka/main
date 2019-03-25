package seedu.address.logic.parser;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.AddMilestoneToCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.project.Client;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.UserStory;
import seedu.address.model.project.UserStoryFunction;
import seedu.address.model.project.UserStoryImportance;
import seedu.address.model.project.UserStoryReason;
import seedu.address.model.project.UserStoryUser;
import seedu.address.model.skill.Skill;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String event} into a {@code Milestone}.
     * Leading and trailing whitespaces will be trimmed and event split into
     * description and date to check for validity
     *
     * @throws ParseException if the given {@code event} is invalid.
     */
    public static Milestone parseMilestone(String event) throws ParseException {
        requireNonNull(event);
        String trimmedMilestone = event.trim();
        int position = trimmedMilestone.lastIndexOf(" ");
        if ((position > trimmedMilestone.length()) || (position < 0)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddMilestoneToCommand.MESSAGE_USAGE));
        }
        String milestoneDesc = trimmedMilestone.substring(0, position);
        String date = trimmedMilestone.substring(position + 1);
        if (!Milestone.isValidMilestone(milestoneDesc.trim(), date.trim())) {
            throw new ParseException(Milestone.MESSAGE_CONSTRAINTS);
        }
        return new Milestone(milestoneDesc, date);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String skill} into a {@code Skill}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code skill} is invalid.
     */
    public static Skill parseSkill(String skill) throws ParseException {
        requireNonNull(skill);
        String trimmedSkill = skill.trim();
        if (!Skill.isValidSkillName(trimmedSkill)) {
            throw new ParseException(Skill.MESSAGE_CONSTRAINTS);
        }
        return new Skill(trimmedSkill);
    }

    /**
     * Parses {@code Collection<String> skills} into a {@code Set<Skill>}.
     */
    public static Set<Skill> parseSkills(Collection<String> skills) throws ParseException {
        requireNonNull(skills);
        final Set<Skill> skillSet = new HashSet<>();
        for (String skillName : skills) {
            skillSet.add(parseSkill(skillName));
        }
        return skillSet;
    }

    /**
     * Parses a {@code String name} into a {@code Client}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code client} is invalid.
     */
    public static Client parseClient(String client) throws ParseException {
        requireNonNull(client);
        String trimmedClient = client.trim();
        if (!Client.isValidName(trimmedClient)) {
            throw new ParseException(Client.MESSAGE_CONSTRAINTS);
        }
        return new Client(trimmedClient);
    }

    /**
     * Parses a {@code String name} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDate = deadline.trim();
        if (!Deadline.isValidDate(trimmedDate)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(trimmedDate);
    }

    /**
     * Parses a {@code String name} into a {@code Project Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code project name} is invalid.
     */
    public static ProjectName parseProjectName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!ProjectName.isValidName(trimmedName)) {
            throw new ParseException(ProjectName.MESSAGE_CONSTRAINTS);
        }
        return new ProjectName(trimmedName);
    }

    /**
     * Parses a {@code String user} into a {@code UserStoryUser user}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code user} is invalid.
     */
    public static UserStoryUser parseStoryUser(String user) throws ParseException {
        requireNonNull(user);
        String trimmedUser = user.trim();
        if (!UserStoryUser.isValidUserStoryUser(trimmedUser)) {
            throw new ParseException(UserStoryUser.MESSAGE_CONSTRAINTS);
        }
        return new UserStoryUser(trimmedUser);
    }

    /**
     * Parses a {@code String function} into a {@code UserStoryFunction function}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static UserStoryFunction parseStoryFunction(String function) {
        requireNonNull(function);
        String trimmedFunction = function.trim();
        return new UserStoryFunction(trimmedFunction);
    }

    /**
     * Parses a {@code String name} into a {@code UserStoryReason reason}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static UserStoryReason parseStoryReason(String reason) {
        String trimmedReason = reason.trim();
        return new UserStoryReason(trimmedReason);
    }

    /**
     * Parses a {@code String importance} into a {@code UserStoryImportance importance}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code input} is invalid.
     */
    public static UserStoryImportance parseStoryImportance(String input) throws ParseException {
        requireNonNull(input);
        String trimmedInput = input.trim();
        if (!UserStoryImportance.isValidImportanceLevel(trimmedInput)) {
            throw new ParseException(UserStoryImportance.MESSAGE_CONSTRAINTS);
        }
        return new UserStoryImportance(trimmedInput);
    }

}
