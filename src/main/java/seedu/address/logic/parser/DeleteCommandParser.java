package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteEmployeeCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Used for separation of delete type word and args.
     */
    private static final Pattern DELETE_COMMAND_FORMAT = Pattern.compile("(?<typeWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns an DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {

        final Matcher matcher = DELETE_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String typeWord = matcher.group("typeWord");
        final String arguments = matcher.group("arguments");

        if (typeWord.equals(DeleteCommand.DELETE_EMPLOYEE_TYPE)) {
            try {
                Index index = ParserUtil.parseIndex(arguments.trim());
                return new DeleteEmployeeCommand(index);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteEmployeeCommand.MESSAGE_USAGE), pe);
            }
        } else if (typeWord.equals(DeleteCommand.DELETE_PROJECT_TYPE)) {
            //to change to delete project later
            try {
                Index index = ParserUtil.parseIndex(arguments.trim());
                return new DeleteEmployeeCommand(index);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteEmployeeCommand.MESSAGE_USAGE), pe);
            }

        } else {
            throw new ParseException (
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE)
            );
        }
    }

}
