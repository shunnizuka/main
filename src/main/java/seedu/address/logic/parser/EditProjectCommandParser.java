package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MILESTONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERSTORY;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.EditProjectCommand;
import seedu.address.logic.commands.EditProjectDefaultCommand.EditProjectDescriptor;
import seedu.address.logic.commands.EditProjectDefaultCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Description;
import seedu.address.model.project.ProjectName;

/**
 * Parse input arguments and creates a new EditProjectCommand object
 */
public class EditProjectCommandParser implements Parser<EditProjectCommand> {

    /**
     * Used for separation of type keyword and args.
     */
    private static final Pattern EDIT_PROJECT_COMMAND_FORMAT = Pattern.compile("(?<project>(\\S+\\s)+)"
        + "(?<args>n/.*|d/.*|desc/.*|c/.*)");

    private static final Pattern EDIT_PROJECT_MILESTONE_OR_USERSTORY_FORMAT = Pattern.compile("(?<project>(\\S+\\s)+)"
        + "(?<keyword>milestone\\s|userstory\\s)(?<arguments>.*)");

    @Override
    public EditProjectCommand parse(String userInput) throws ParseException {

        System.out.println(userInput);
        final Matcher defaultMatcher = EDIT_PROJECT_COMMAND_FORMAT.matcher(userInput.trim());
        final Matcher milestoneOrUserstoryMatcher = EDIT_PROJECT_MILESTONE_OR_USERSTORY_FORMAT
            .matcher(userInput.trim());

        if (defaultMatcher.matches()) {
            int indexOfArgs = findArgsPositionDefault(userInput);
            String projectName = userInput.substring(0, indexOfArgs - 1);
            String args = userInput.substring(indexOfArgs);
            System.out.println(projectName);
            System.out.println(args);

            ProjectName name;
            try {
                name = ParserUtil.parseProjectName(projectName);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditProjectCommand.MESSAGE_USAGE), pe);
            }

            requireNonNull(args);
            String s = " " + args;
            ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(s, PREFIX_NAME, PREFIX_CLIENT, PREFIX_DEADLINE, PREFIX_DESCRIPTION);

            EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();

            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                editProjectDescriptor.setProjectName(ParserUtil.parseProjectName(argMultimap
                    .getValue(PREFIX_NAME).get()));
                System.out.println(argMultimap.getValue(PREFIX_NAME).get());
            }
            if (argMultimap.getValue(PREFIX_CLIENT).isPresent()) {
                editProjectDescriptor.setClient(ParserUtil.parseClient(argMultimap.getValue(PREFIX_CLIENT).get()));
                System.out.println(argMultimap.getValue(PREFIX_CLIENT).get());
            }
            if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
                editProjectDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap
                    .getValue(PREFIX_DEADLINE).get()));
                System.out.println(argMultimap.getValue(PREFIX_DEADLINE).get());
            }
            if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
                editProjectDescriptor.setDescription(new Description(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
                System.out.println(argMultimap.getValue(PREFIX_DESCRIPTION).get());
            }

            if (!editProjectDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditProjectDefaultCommand.MESSAGE_NOT_EDITED);
            }

            return new EditProjectDefaultCommand(name, editProjectDescriptor);

        } else if (milestoneOrUserstoryMatcher.matches()) {

            System.out.println("correct");
            int indexOfArgs = findArgsPosition(userInput);
            String projectName = userInput.substring(0, indexOfArgs - 1);
            String args = userInput.substring(indexOfArgs);
            System.out.println(projectName);
            System.out.println(args);

            return null;

        } else {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProjectCommand.MESSAGE_USAGE));
        }

    }

    /**
     * To look for the index of the argument in the editProjectDefault command
     * The index will be used for extracting keyword and prefixes from the command
     * @param userinput the editProjectDefultCommand
     * @return the index of the argument in the command
     */
    private Integer findArgsPositionDefault(String userinput) {

        Integer index = Integer.MAX_VALUE;
        if (userinput.contains(PREFIX_NAME.getPrefix())) {
            if (userinput.indexOf(PREFIX_NAME.getPrefix()) < index) {
                index = userinput.indexOf("n/");
            }
        }
        if (userinput.contains(PREFIX_CLIENT.getPrefix())) {
            if (userinput.indexOf(PREFIX_CLIENT.getPrefix()) < index) {
                index = userinput.indexOf(PREFIX_CLIENT.getPrefix());
            }
        }
        if (userinput.contains(PREFIX_DEADLINE.getPrefix())) {
            if (userinput.indexOf(PREFIX_DEADLINE.getPrefix()) < index) {
                index = userinput.indexOf(PREFIX_DEADLINE.getPrefix());
            }
        }
        if (userinput.contains(PREFIX_DESCRIPTION.getPrefix())) {
            if (userinput.indexOf(PREFIX_DESCRIPTION.getPrefix()) < index) {
                index = userinput.indexOf(PREFIX_DESCRIPTION.getPrefix());
            }
        }
        return index;
    }

    /**
     * To look for the index of the argument in the editProjectMilestone or editProjectMilestone command
     * The index will be used for extracting keyword and prefixes from the command
     * @param userinput the editProjectMilestone or editProjectMilestone
     * @return the index of the argument in the command
     */
    private Integer findArgsPosition(String userinput) {

        Integer index = Integer.MAX_VALUE;

        if (userinput.contains(PREFIX_USERSTORY.getPrefix())) {
            if (userinput.indexOf(PREFIX_USERSTORY.getPrefix()) < index) {
                index = userinput.indexOf(PREFIX_USERSTORY.getPrefix());
            }
        }

        if (userinput.contains(PREFIX_MILESTONE.getPrefix())) {
            if (userinput.indexOf(PREFIX_MILESTONE.getPrefix()) < index) {
                index = userinput.indexOf(PREFIX_MILESTONE.getPrefix());
            }
        }
        return index;
    }

}

