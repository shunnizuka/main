package seedu.address.logic.parser;

import static seedu.address.model.project.UserStoryFunction.PREAMBLE_FUNCTION;
import static seedu.address.model.project.UserStoryReason.PREAMBLE_REASON;
import static seedu.address.model.project.UserStoryUser.PREAMBLE_USER;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_GITHUB = new Prefix("g/");
    public static final Prefix PREFIX_SKILL = new Prefix("s/");
    public static final Prefix PREFIX_CLIENT = new Prefix("c/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("d/");

    /* User story prefixes */
    public static final Prefix PREFIX_USER = new Prefix(PREAMBLE_USER);
    public static final Prefix PREFIX_FUNCTION = new Prefix(PREAMBLE_FUNCTION);
    public static final Prefix PREFIX_REASON = new Prefix(PREAMBLE_REASON);
    public static final Prefix PREFIX_IMPORTANCE = new Prefix("i/");

    /* Flexible date definitions */
    public static final Prefix FUTURE_KEYWORD = new Prefix("next");
    public static final Prefix CURRENT_KEYWORD = new Prefix("this");
    public static final Prefix PAST_KEYWORD = new Prefix("last");
    public static final Prefix TODAY_KEYWORD = new Prefix("today");
    public static final Prefix TOMORROW_KEYWORD = new Prefix("tomorrow");
    public static final Prefix YESTERDAY_KEYWORD = new Prefix("yesterday");

}
