package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddEmployeeToCommand;
import seedu.address.logic.commands.AddMilestoneToCommand;
import seedu.address.logic.commands.AddToCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.ProjectName;

/**
 * Parses input arguments and creates a new AddToCommand object
 */
public class AddToCommandParser implements Parser<AddToCommand> {

    /**
     * Used for separation of type keyword and args.
     */
    private static final Pattern ADD_TO_COMMAND_FORMAT = Pattern.compile("(?<project>(\\S+\\s)+)"
            + "(?<keyword>employee\\s|milestone\\s)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the AddToCommand
     * and returns an AddToCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddToCommand parse(String args) throws ParseException {

        final Matcher matcher = ADD_TO_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE));
        }
        final ProjectName projectName = new ProjectName(matcher.group("project").trim());
        final String keyword = matcher.group("keyword").trim().toLowerCase();
        final String arguments = matcher.group("arguments");
        if (keyword.equals(AddEmployeeToCommand.ADD_EMPLOYEE_KEYWORD)) {
            try {
                Index index = ParserUtil.parseIndex(arguments.trim());
                return new AddEmployeeToCommand(index, projectName);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEmployeeToCommand.MESSAGE_USAGE), pe);
            }
        } else if (keyword.equals(AddMilestoneToCommand.ADD_MILESTONE_KEYWORD)) {
            try {
                Milestone milestone = ParserUtil.parseMilestone(arguments.trim());
                return new AddMilestoneToCommand(projectName, milestone);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMilestoneToCommand.MESSAGE_USAGE), pe);
            }

        } else {
            throw new ParseException (
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE)
            );
        }
    }

}
