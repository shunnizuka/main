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
import seedu.address.model.skill.EmployeeSkillContainsKeywordsPredicate;

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

        assertParseSuccess(parser, "employee \t Alice \t Bob  \t", expectedFindCommand);
        assertParseSuccess(parser, "e Alice Bob", expectedFindCommand);

        expectedFindCommand =
            new FindProjectCommand(new ProjectNameContainsKeywordsPredicate(Arrays.asList("Apollo", "Gemini")));
        assertParseSuccess(parser, FindProjectCommand.FIND_PROJECT_KEYWORD + " Apollo Gemini",
            expectedFindCommand);
        assertParseSuccess(parser, "project \t Apollo  Gemini \t", expectedFindCommand);
        assertParseSuccess(parser, "p Apollo Gemini", expectedFindCommand);

        expectedFindCommand =
            new FindSkillCommand(new EmployeeSkillContainsKeywordsPredicate(Arrays.asList("java", "html")));
        assertParseSuccess(parser, FindSkillCommand.FIND_SKILL_KEYWORD + " java html",
            expectedFindCommand);
        assertParseSuccess(parser, "skill \t java \t html  \t", expectedFindCommand);
        assertParseSuccess(parser, "s java html", expectedFindCommand);
    }

}
