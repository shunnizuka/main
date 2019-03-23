package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveEmployeeFromCommand;
import seedu.address.logic.commands.RemoveFromCommand;
import seedu.address.logic.commands.RemoveMilestoneFromCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.ProjectName;

/**
 * Parses input arguments and creates a new RemoveFromCommand object
 */
public class RemoveFromCommandParser implements Parser<RemoveFromCommand> {

    /**
     * Used for separation of type keyword and args.
     */
    private static final Pattern REMOVE_FROM_COMMAND_FORMAT = Pattern.compile("(?<project>(\\S+\\s)+)"
        + "(?<keyword>employee\\s|milestone\\s)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveFromCommand
     * and returns an RemoveFromCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveFromCommand parse(String args) throws ParseException {

        final Matcher matcher = REMOVE_FROM_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveFromCommand.MESSAGE_USAGE));
        }
        final ProjectName projectName = new ProjectName(matcher.group("project").trim());
        final String keyword = matcher.group("keyword").trim();
        final String arguments = matcher.group("arguments");
        if (keyword.equals(RemoveEmployeeFromCommand.REMOVE_EMPLOYEE_KEYWORD) || keyword.equals("e")) {
            try {
                Index index = ParserUtil.parseIndex(arguments.trim());
                return new RemoveEmployeeFromCommand(index, projectName);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveEmployeeFromCommand.MESSAGE_USAGE), pe);
            }
        } else if (keyword.equals(RemoveMilestoneFromCommand.REMOVE_MILESTONE_KEYWORD) || keyword.equals("m")) {
            try {
                Index index = ParserUtil.parseIndex(arguments.trim());
                return new RemoveMilestoneFromCommand(index, projectName);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMilestoneFromCommand.MESSAGE_USAGE), pe);
            }

        } else {
            throw new ParseException (
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveFromCommand.MESSAGE_USAGE)
            );
        }
    }

}
