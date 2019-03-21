package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewEmployeeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewEmployeeCommand object
 */
public class SelectCommandParser implements Parser<ViewEmployeeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewEmployeeCommand
     * and returns an ViewEmployeeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewEmployeeCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewEmployeeCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewEmployeeCommand.MESSAGE_USAGE), pe);
        }
    }
}
