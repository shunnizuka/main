package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindEmployeeCommand;
import seedu.address.logic.commands.FindProjectCommand;
import seedu.address.logic.commands.FindSkillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.EmployeeNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindEmployeeCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static final Pattern FIND_COMMAND_FORMAT = Pattern.compile("(?<keyword>\\S+)(?<arguments>.*)");
    /**
     * Parses the given {@code String} of arguments in the context of the FindEmployeeCommand
     * and returns an FindEmployeeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        final Matcher matcher = FIND_COMMAND_FORMAT.matcher(trimmedArgs);

        if (!matcher.matches()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        final String keyword = matcher.group("keyword");
        final String arguments = matcher.group("arguments");
        String[] nameKeywords = arguments.trim().split("\\s+");

        if (keyword.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEmployeeCommand.MESSAGE_USAGE));
        }

        switch (keyword) {

        case FindEmployeeCommand.FIND_EMPLOYEE_KEYWORD:
            return new FindEmployeeCommand(new EmployeeNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

        case FindProjectCommand.FIND_PROJECT_KEYWORD:
            return new FindProjectCommand(new EmployeeNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

        case FindSkillCommand.FIND_SKILL_KEYWORD:
            return new FindSkillCommand(new EmployeeNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

        default:
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEmployeeCommand.MESSAGE_USAGE));
        }
    }

}
