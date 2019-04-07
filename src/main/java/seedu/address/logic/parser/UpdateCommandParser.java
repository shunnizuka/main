package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateCommand;
import seedu.address.logic.commands.UpdateUserStoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.UserStoryStatus;

/**
 * Parses input arguments and creates a new UpdateCommand object
 */
public class UpdateCommandParser implements Parser<UpdateCommand> {

    private static final Pattern UPDATE_COMMAND_FORMAT = Pattern.compile("(?<project>(\\S+\\s)+)"
            + "(?<keyword>userstory\\s|projecttask\\s)(?<arguments>.*)");

    private static final Pattern UPDATE_USER_STORY_FORMAT = Pattern.compile("(?<index>\\d)\\s+"
            + "(?<status>ongoing|on hold|complete)");

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateCommand
     * and returns an UpdateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public UpdateCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();
        final Matcher matcher = UPDATE_COMMAND_FORMAT.matcher(trimmedArgs);

        if (!matcher.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE));
        }

        final ProjectName projectName = new ProjectName(matcher.group("project").trim());
        final String keyword = matcher.group("keyword").trim().toLowerCase();
        final String arguments = matcher.group("arguments");

        if (keyword.equals(UpdateUserStoryCommand.UPDATE_STORY_KEYWORD)) {
            try {
                String trimmedArg = arguments.trim();
                Matcher storyMatcher = UPDATE_USER_STORY_FORMAT.matcher(trimmedArg);

                if (!storyMatcher.matches()) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateUserStoryCommand.MESSAGE_USAGE)
                    );
                }

                final String storyIndex = storyMatcher.group("index");
                final String newStatus = storyMatcher.group("status");
                final UserStoryStatus status = ParserUtil.parseStoryStatus(newStatus);
                final Index index = ParserUtil.parseIndex(storyIndex);

                return new UpdateUserStoryCommand(projectName, index, status);

            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateUserStoryCommand.MESSAGE_USAGE), pe);
            }
        } else {
            throw new ParseException (
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE)
            );
        }
    }
}

