package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindEmployeeCommand;
import seedu.address.logic.commands.FindProjectCommand;
import seedu.address.logic.commands.FindSkillCommand;
import seedu.address.model.employee.EmployeeNameContainsKeywordsPredicate;
import seedu.address.model.project.ProjectNameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, FindEmployeeCommand.FIND_EMPLOYEE_KEYWORD + "  ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser, FindProjectCommand.FIND_PROJECT_KEYWORD + "  ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser, FindSkillCommand.FIND_SKILL_KEYWORD + "  ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
            new FindEmployeeCommand(new EmployeeNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, FindEmployeeCommand.FIND_EMPLOYEE_KEYWORD
            + " Alice Bob", expectedFindCommand);

        expectedFindCommand =
            new FindProjectCommand(new ProjectNameContainsKeywordsPredicate(Arrays.asList("Apollo", "Gemini")));
        assertParseSuccess(parser, FindProjectCommand.FIND_PROJECT_KEYWORD + " Apollo Gemini",
            expectedFindCommand);

        //TODO: to change the predicate after setting up FindSkillCommand
        expectedFindCommand =
            new FindSkillCommand(new EmployeeNameContainsKeywordsPredicate(Arrays.asList("java", "html")));
        assertParseSuccess(parser, FindSkillCommand.FIND_SKILL_KEYWORD + " java html",
            expectedFindCommand);

        // multiple whitespaces between keywords
        //TODO: need to fix parser to allow multiple whitespaces between keywords
        //assertParseSuccess(parser, "employee Alice \n \t Bob  \t", expectedFindCommand);
    }

}
