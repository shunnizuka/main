package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveEmployeeFromCommand;
import seedu.address.logic.commands.RemoveFromCommand;
import seedu.address.logic.commands.RemoveMilestoneFromCommand;
import seedu.address.model.project.ProjectName;
/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the RemoveFromCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class RemoveFromCommandParserTest {

    private RemoveFromCommandParser parser = new RemoveFromCommandParser();

    @Test
    public void parse_validArgs_returnsRemoveFromCommand() {
        assertParseSuccess(parser, "Project Apollo employee 1",
                new RemoveEmployeeFromCommand(Index.fromOneBased(1), new ProjectName("Project Apollo")));
        assertParseSuccess(parser, "Project X milestone 2",
                new RemoveMilestoneFromCommand(Index.fromOneBased(2), new ProjectName("Project X")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "Project Apollo invalid 1",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RemoveFromCommand.MESSAGE_USAGE)); //wrong format
        assertParseFailure(parser, "Project Apollo employee 0",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RemoveEmployeeFromCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "Project Apollo milestone 0",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RemoveMilestoneFromCommand.MESSAGE_USAGE));
    }
}
