package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.FindEmployeeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindEmployeeCommand object
 */
public class FindCommandParser implements Parser<FindEmployeeCommand> {

    private static final Pattern FIND_COMMAND_FORMAT = Pattern.compile("(?<type>\\S+)(?<keyword>.*)");
    /**
     * Parses the given {@code String} of arguments in the context of the FindEmployeeCommand
     * and returns an FindEmployeeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    
    public FindEmployeeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        final Matcher matcher = FIND_COMMAND_FORMAT.matcher(trimmedArgs);
        
        if (!matcher.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEmployeeCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        final String type = matcher.group("type");
        final String keyword = matcher.group("keyword");

        switch (type) { 
            
            case FindEmployeeCommand.TYPE: 
                return new FindEmployeeCommand(new NameContainsKeywordsPredicate(Arrays.asList(keyword)));
                
            case FindEmployeeCommand.FINDPROJECTTYPE:
                System.out.println("Parser: finding project....");
                return new FindEmployeeCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
                
            case FindEmployeeCommand.FINDSKILLTYPE:
                System.out.println("Parser: finding skill....");
                return new FindEmployeeCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
                
            default:
                    return new FindEmployeeCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
                
        }
    }

}
