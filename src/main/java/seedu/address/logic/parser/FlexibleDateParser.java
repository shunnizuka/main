package seedu.address.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.FlexibleDate;

/**
 * Parses the flexible date input.
 */
public class FlexibleDateParser {

    /**
     * Used for initial separation of flexible date command word and args.
     */
    private static final Pattern BASIC_FLEXIDATE_FORMAT = Pattern.compile("(?<keyword>\\S+)(?<arguments>.*)");

    /**
     * To check if input is given in the fixed format.
     */
    private static final String ALPHA_NUMERIC_VALIDATION_REGEX = "^[a-zA-Z0-9]*$";

    private static final int NEXT = 1;
    private static final int LAST = -1;
    private static final int CURRENT = 0;
    private static final int DAY_ZERO = 0;

    /**
     * Parses user flexible date input into fixed format string for processing.
     * @param dateInput flexible date input by user
     * @return a date string in the form of DD/MM/YYYY
     * @throws ParseException if the user input does not conform the expected format
     */
    public static String parseFlexibleDate(String dateInput) throws ParseException {

        if(!isFlexibleInput(dateInput)) {
            return dateInput;
        } else {
            final Matcher matcher = BASIC_FLEXIDATE_FORMAT.matcher(dateInput.trim());
            if (!matcher.matches()) {
                throw new ParseException(Deadline.MESSAGE_CONSTRAINTS); //TODO change msg constraints
            }

            final String keyword = matcher.group("keyword").toLowerCase();
            final String arguments = matcher.group("arguments");

            switch(keyword) {

            case CliSyntax.TODAY_KEYWORD:
                return formatDate(CURRENT);

            case CliSyntax.TOMORROW_KEYWORD:
                return formatDate(NEXT);

            case CliSyntax.YESTERDAY_KEYWORD:
                return formatDate(LAST);

            default:
                throw new ParseException(Deadline.MESSAGE_CONSTRAINTS); //TODO CHANGE Message
            }
        }
    }

    private static boolean isFlexibleInput(String flexibleDateInput) {
        return flexibleDateInput.matches(ALPHA_NUMERIC_VALIDATION_REGEX);
    }

    /**
     * Gets and formats target date based on DD/MM/YYYY
     * @return formatted target date.
     */
    private static String formatDate(int days) {

        FlexibleDate date = new FlexibleDate();

        if (days != DAY_ZERO) {
            return date.dateNumDaysLater(days);
        } else {
             return date.currentDate();
        }
    }
}
