package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindEmployeeCommand;
import seedu.address.model.employee.EmployeeNameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindEmployeeCommand expectedFindCommand =
            new FindEmployeeCommand(new EmployeeNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "employee Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        //TODO: need to fix parser to allow multiple whitespaces between keywords
        //assertParseSuccess(parser, "employee Alice \n \t Bob  \t", expectedFindCommand);
    }

}
