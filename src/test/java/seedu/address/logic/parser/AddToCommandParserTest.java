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
    public void parse_validArgs_returnsAddToCommand() {
        assertParseSuccess(parser, "Project Apollo employee 1",
                new AddEmployeeToCommand(Index.fromOneBased(1), new ProjectName("Project Apollo")));

        assertParseSuccess(parser, "Project X employee 3",
                new AddEmployeeToCommand(Index.fromOneBased(3), new ProjectName("Project X")));

        assertParseSuccess(parser, "Project Apollo milestone Completed UG 23/04/2019",
                new AddMilestoneToCommand(new ProjectName("Project Apollo"), new Milestone("Completed UG",
                    "23/04/2019")));

        assertParseSuccess(parser, "Transformium milestone Completed DG Today 25/09/2019",
                new AddMilestoneToCommand(new ProjectName("Transformium"), new Milestone("Completed DG Today",
                        "25/09/2019")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {

        //wrong format, no keyword
        assertParseFailure(parser, "Project Apollo invalid 1",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE));

        //index of employee cannot be 0
        assertParseFailure(parser, "Project Apollo employee 0",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddEmployeeToCommand.MESSAGE_USAGE));

        //index of employee cannot be non-number
        assertParseFailure(parser, "Project Apollo employee xx",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddEmployeeToCommand.MESSAGE_USAGE));

        //date should be in the format of DD/MM/YYYY
        assertParseFailure(parser, "Project Apollo milestone Completed 233/04/2019",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddMilestoneToCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingArgs_failure() {

    //missing project name
    assertParseFailure(parser, "employee 1",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE));

    //missing keyword
    assertParseFailure(parser, "Project Apollo 1",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE));

    //missing employee index
    assertParseFailure(parser, "Project Apollo employee",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE));

    //missing date
    assertParseFailure(parser, "Project Apollo milestone Completed",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddMilestoneToCommand.MESSAGE_USAGE));

    //empty missing description
    assertParseFailure(parser, "Project Apollo milestone 23/04/1996",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddMilestoneToCommand.MESSAGE_USAGE));

    }
}
