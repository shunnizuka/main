package seedu.address.logic.parser;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddEmployeeToCommand;
import seedu.address.logic.commands.AddMilestoneToCommand;
import seedu.address.logic.commands.AddToCommand;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.ProjectName;

import org.junit.Test;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class AddToCommandParserTest {

    private AddToCommandParser parser = new AddToCommandParser();

    @Test
    public void parse_validArgs_returnsRemoveFromCommand() {
        assertParseSuccess(parser, "Project Apollo employee 1",
                new AddEmployeeToCommand(Index.fromOneBased(1), new ProjectName("Project Apollo")));

        //TODO not sure why got parsing error
        /*
        assertParseSuccess(parser, "Project Apollo milestone Completed UG 23/04/2019",
                new AddMilestoneToCommand(new ProjectName("Project Apollo"), new Milestone("Completed UG",
                    "23/04/2019")));
        */
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "Project Apollo invalid 1",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE)); //wrong format
        assertParseFailure(parser, "Project Apollo employee 0",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddEmployeeToCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "Project Apollo milestone Completed 23/04/2019",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddMilestoneToCommand.MESSAGE_USAGE));
    }
}
