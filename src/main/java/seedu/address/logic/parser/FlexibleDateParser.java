package seedu.address.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Deadline;

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
    private static final String ALPHANUMERIC_VALIDATION_REGEX = "^\\S+[a-zA-Z0-9]*$";

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






            }

        }



    }

    private static boolean isFlexibleInput(String flexibleDateInput) {
        return flexibleDateInput.matches(ALPHANUMERIC_VALIDATION_REGEX);
    }
}
