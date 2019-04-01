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
    public static final Prefix PREFIX_MILESTONE = new Prefix("m/");

    /* User story prefixes */
    public static final Prefix PREFIX_USER = new Prefix(PREAMBLE_USER);
    public static final Prefix PREFIX_FUNCTION = new Prefix(PREAMBLE_FUNCTION);
    public static final Prefix PREFIX_REASON = new Prefix(PREAMBLE_REASON);
    public static final Prefix PREFIX_IMPORTANCE = new Prefix("i/");

    /* Flexible date definitions */
    public static final Prefix PREFIX_CURRENT = new Prefix("this");
    public static final Prefix PREFIX_FUTURE = new Prefix("next");
    public static final Prefix PREFIX_MONTH = new Prefix("month");
    public static final Prefix PREFIX_PAST = new Prefix("last");
    public static final Prefix PREFIX_TODAY = new Prefix("today");
    public static final Prefix PREFIX_TOMORROW = new Prefix("tomorrow");
    public static final Prefix PREFIX_WEEK = new Prefix("week");
    public static final Prefix PREFIX_YESTERDAY = new Prefix("yesterday");

}
