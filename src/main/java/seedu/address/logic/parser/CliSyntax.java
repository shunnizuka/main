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
    
    /* Project prefixes*/
    public static final Prefix PREFIX_CLIENT = new Prefix("c/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("d/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("desc/");
    public static final Prefix PREFIX_MILESTONE = new Prefix("milestone");
    public static final Prefix PREFIX_USERSTORY = new Prefix("userstory");

    /* User story prefixes */
    public static final Prefix PREFIX_USER = new Prefix(PREAMBLE_USER);
    public static final Prefix PREFIX_FUNCTION = new Prefix(PREAMBLE_FUNCTION);
    public static final Prefix PREFIX_REASON = new Prefix(PREAMBLE_REASON);
    public static final Prefix PREFIX_IMPORTANCE = new Prefix("i/");
}
