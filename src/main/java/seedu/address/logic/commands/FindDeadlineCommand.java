package seedu.address.logic.commands;

/**
 * Find and list all the projects in Pocket Project which contains deadline which are before the deadline keyword
 * input by the user
 * Also accepts flexible dates
 */
public class FindDeadlineCommand extends FindCommand {
    
    public static final String FIND_DEADLINE_KEYWORD = "deadline";
    
    public static final String MESSAGE_USAGE = FindCommand.COMMAND_WORD + " " + FIND_DEADLINE_KEYWORD + " [ARGUMENTS]";
    
    
}
