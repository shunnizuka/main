package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.EditProjectCommand;
import seedu.address.logic.commands.EditProjectDefaultCommand.EditProjectDescriptor;
import seedu.address.logic.commands.EditProjectDefaultCommand;
import seedu.address.logic.commands.EditProjectMilestoneCommand;
import seedu.address.logic.commands.EditProjectUserStoryCommand;
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
        + "(?<keyword>milestone\\s|userstory\\s|info\\s)(?<arguments>.*)");

    @Override
    public EditProjectCommand parse(String userInput) throws ParseException {

        System.out.println(userInput);
        final Matcher matcher = EDIT_PROJECT_COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProjectCommand.MESSAGE_USAGE));
        }

        int indexOfArgs = findKeywordPosition(userInput);
        String projectName = userInput.substring(0, indexOfArgs - 1);
        //args containing keyword
        String argsString = userInput.substring(indexOfArgs);
        //to extract out the keyword
        String[] args = argsString.split(" ", 2);
        String keyword = args[0];
        String arguments = args[1];

        ProjectName name;
        try {
            name = ParserUtil.parseProjectName(projectName);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditProjectCommand.MESSAGE_USAGE), pe);
        }

        if (keyword.equals(EditProjectDefaultCommand.EDIT_INFO_KEYWORD)) {
            requireNonNull(arguments);
            String s = " " + arguments;
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

        } else {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProjectCommand.MESSAGE_USAGE));
        }

    }

    /**
     * To look for the index of the keyword in the editProject command
     * The index will be used for extracting keyword and prefixes from the command
     * @param userinput the editProjectMilestone or editProjectMilestone
     * @return the index of the argument in the command
     */
    private Integer findKeywordPosition(String userinput) {

        Integer index = Integer.MAX_VALUE;

        if (userinput.contains(EditProjectDefaultCommand.EDIT_INFO_KEYWORD)) {
            if (userinput.indexOf(EditProjectDefaultCommand.EDIT_INFO_KEYWORD) < index) {
                index = userinput.indexOf(EditProjectDefaultCommand.EDIT_INFO_KEYWORD);
            }
        }

        if (userinput.contains(EditProjectMilestoneCommand.EDIT_MILESTONE_KEYWORD)) {
            if (userinput.indexOf(EditProjectMilestoneCommand.EDIT_MILESTONE_KEYWORD) < index) {
                index = userinput.indexOf(EditProjectMilestoneCommand.EDIT_MILESTONE_KEYWORD);
            }
        }

        if (userinput.contains(EditProjectUserStoryCommand.EDIT_USER_STORY_KEYWORD)) {
            if (userinput.indexOf(EditProjectUserStoryCommand.EDIT_USER_STORY_KEYWORD) < index ) {
                index = userinput.indexOf(EditProjectUserStoryCommand.EDIT_USER_STORY_KEYWORD);
            }
        }
        return index;
    }

}

