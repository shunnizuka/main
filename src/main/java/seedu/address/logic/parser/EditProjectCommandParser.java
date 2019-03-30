package seedu.address.logic.parser;

import seedu.address.logic.commands.EditProjectCommand;

import java.util.regex.Pattern;

/**
 * Parse input arguments and creates a new EditProjectCommand object
 */
public class EditProjectCommandParser implements Parser<EditProjectCommand> {

    /**
     * Used for separation of type keyword and args.
     */
    private static final Pattern EDIT_PROJECT_COMMAND_FORMAT = Pattern.compile("(?<project>(\\S+\\s)+)"
        + "(?<keyword>milestone\\s|userstory\\s)(?<arguments>.*)");
    
    
}

