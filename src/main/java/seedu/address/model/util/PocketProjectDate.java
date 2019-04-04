package seedu.address.model.util;

import seedu.address.model.project.Project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class that it used to present all date objects use throughout the application
 */

public class PocketProjectDate extends CalendarDate {

    public static final String MESSAGE_CONSTRAINTS = "Deadlines should be in the format DD/MM/YYYY. User can also "
        + "choose to go for a flexible date input which supports the following: today, tomorrow, yesterday, "
        + "this/next/last month DAY_OF_MONTH & "
        + "this/next/last week DAY_OF_WEEK";

    public static final String DAY_OF_WEEK_MONTH_CONSTRAINTS = "The day of week has to be an integer between 1 and 7 "
        + "inclusive and the day of month has to be an integer between 1 and 31 and correspond to the max number of "
        + "days in particular month";

    public static final String FLEXI_DATE_MESSAGE_CONSTRAINTS = "Flexible date inputs only allow choosing between "
        + "this/next/last week or month. They keyword week/month has to be included";

    /**
     * Target date format.
     */
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Character used to identify dates in DD/MM/YYYY.
     */
    private static final String DATE_IDENTIFIER = "/";

    /**
     * Number of components in a date.
     */
    private static final int NUM_COMPONENTS = 4;

    /**
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

    private static final int LAST = -1;
    private static final int LENGTH_OF_WEEK = 7;
    private static final int NEXT = 1;

    public final String date;

    /**
     * Constructor to create a Pocket Project Date object when receiving fixed entries.
     * @param date string that is expected to be in the DD/MM/YYYY format
     */
    public PocketProjectDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = date;
    }

    /**
     * Constructor to create a Pocket Project Date object when receiving flexible entries.
     */
    public PocketProjectDate() {
        this.date = DATE_FORMAT.format(LocalDateTime.now());
    }

    /**
     * Constructor to create a Pocket Project Date object with a LocalDateTime object as input.
     * @param date that date to be stored.
     */
    public PocketProjectDate(LocalDateTime date) {
        this.date = DATE_FORMAT.format(date);
    }

    /**
     * Method that returns the current date based on local time zone.
     * @return current date according to given format
     */
    public String currentDate() {
        return DATE_FORMAT.format(LocalDateTime.now());
    }

    /**
     * Method that adds specified number of days to current date.
     * @param numDays number of days to be added.
     * @return target date according to given format
     */
    public String dateNumDaysLater(long numDays) {

        return DATE_FORMAT.format(convertToLocalDateTimeFormat(date).plusDays(numDays));
    }

    /**
     * Method that subtracts specified number of days from current date.
     * @param numDays number of days to be subtracted.
     * @return target date according to given format
     */
    public String dateNumDaysBefore(long numDays) {
        return DATE_FORMAT.format(convertToLocalDateTimeFormat(date).minusDays(numDays));
    }

    /**
     * Method that adds specified number of weeks to current date.
     * @param numWeeks number of weeks to be added.
     * @return target date according to given format
     */
    public String dateNumWeeksLater(long numWeeks) {
        return DATE_FORMAT.format(convertToLocalDateTimeFormat(date).plusWeeks(numWeeks));
    }
    /**
     * Method that subtracts specified number of weeks from current date.
     * @param numWeeks number of weeks to be subtracted.
     * @return target date according to given format
     */
    public String dateNumWeeksBefore(long numWeeks) {
        return DATE_FORMAT.format(convertToLocalDateTimeFormat(date).minusWeeks(numWeeks));
    }

    /**
     * Method that adds specified number of months to current date.
     * @param numMonths number of weeks to be added.
     * @return target date according to given format
     */
    public String dateNumMonthsLater(long numMonths) {
        return DATE_FORMAT.format(convertToLocalDateTimeFormat(date).plusMonths(numMonths));
    }

    /**
     * Method that subtracts specified number of months from current date.
     * @param numMonths number of weeks to be subtracted.
     * @return target date according to given format
     */
    public String dateNumMonthsBefore(long numMonths) {
        return DATE_FORMAT.format(convertToLocalDateTimeFormat(date).minusMonths(numMonths));
    }

    /**
     * Method that adds specified number of years to current date.
     * @param numYears number of weeks to be added.
     * @return target date according to given format
     */
    public String dateNumYearsLater(long numYears) {
        return DATE_FORMAT.format(convertToLocalDateTimeFormat(date).plusYears(numYears));
    }

    /**
     * Method that subtracts specified number of years from current date.
     * @param numYears number of weeks to be subtracted.
     * @return target date according to given format
     */
    public String dateNumYearsBefore(long numYears) {
        return DATE_FORMAT.format(convertToLocalDateTimeFormat(date).minusYears(numYears));
    }

    /**
     * Method that calculated date of this week using target day.
     * @param targetDayOfWeek target day of the week
     * @return target date according to given format
     */
    public String thisWeekDate(int targetDayOfWeek) {
        int currentDayOfWeek = LocalDateTime.now().getDayOfWeek().getValue();
        LocalDateTime newDate = convertToLocalDateTimeFormat(date).plusDays(targetDayOfWeek - currentDayOfWeek);
        return DATE_FORMAT.format(newDate);
    }

    /**
     * Method that calculated date of this month using target day.
     * @param targetDayOfMonth target day of the month
     * @return target date according to given format
     */
    public String thisMonthDate(int targetDayOfMonth) {
        int currentDayOfMonth = LocalDateTime.now().getDayOfMonth();
        LocalDateTime newDate = convertToLocalDateTimeFormat(date).plusDays(targetDayOfMonth - currentDayOfMonth);
        return DATE_FORMAT.format(newDate);
    }

    /**
     * Method that calculated date of next week using target day.
     * @param targetDayOfWeek target day of the week
     * @return target date according to given format
     */
    public String nextWeekDate(int targetDayOfWeek) {
        int currentDayOfWeek = LocalDateTime.now().getDayOfWeek().getValue();
        LocalDateTime newDate = convertToLocalDateTimeFormat(date).plusDays(LENGTH_OF_WEEK);
        return DATE_FORMAT.format(newDate.plusDays(targetDayOfWeek - currentDayOfWeek));
    }

    /**
     * Method that calculated date of next month using target day.
     * @param targetDayOfMonth target day of the month
     * @return target date according to given format
     */
    public String nextMonthDate(int targetDayOfMonth) {
        int currentMonth = LocalDateTime.now().getMonth().getValue();
        int currentDayOfMonth = LocalDateTime.now().getDayOfMonth();
        LocalDateTime targetDate = LocalDateTime.now().withMonth(currentMonth + NEXT);
        LocalDateTime newDate = targetDate.plusDays(targetDayOfMonth - currentDayOfMonth + NEXT); //TODO check bug
        return DATE_FORMAT.format(newDate);
    }

    /**
     * Method that calculated date of last week using target day.
     * @param targetDayOfWeek target day of the week
     * @return target date according to given format
     */
    public String lastWeekDate(int targetDayOfWeek) {
        int currentDayOfWeek = LocalDateTime.now().getDayOfWeek().getValue();
        LocalDateTime newDate = convertToLocalDateTimeFormat(date).minusDays(LENGTH_OF_WEEK);
        return DATE_FORMAT.format(newDate.plusDays(targetDayOfWeek - currentDayOfWeek));
    }

    /**
     * Method that calculated date of last month using target day.
     * @param targetDayOfMonth target day of the month
     * @return target date according to given format
     */
    public String lastMonthDate(int targetDayOfMonth) {
        int currentMonth = LocalDateTime.now().getMonth().getValue();
        int currentDayOfMonth = LocalDateTime.now().getDayOfMonth();
        LocalDateTime targetDate = LocalDateTime.now().withMonth(currentMonth + LAST);
        LocalDateTime newDate = targetDate.plusDays(targetDayOfMonth - currentDayOfMonth + NEXT); //TODO check bug
        return DATE_FORMAT.format(newDate);
    }

    /**
     * Returns true if a given string is a valid date.
     * @param input the fixed format date taken in.
     * @return true is valid format DD/MM/YYYY, otherwise false.
     */
    public static boolean isValidDate(String input) {
        DateFormat format = Project.DATE_FORMAT;
        format.setLenient(false);
        try {
            format.parse(input);
        } catch (ParseException e) {
            return false;
        }
        return input.matches(VALIDATION_REGEX);
    }

    /**
     * Used to reformat string DD/MM/YYYY to LocalDateTime object.
     * @param input the DD/MM/YYYY string
     * @return the date as a LocalDateTime object.
     */

    private LocalDateTime convertToLocalDateTimeFormat(String input) {

        String[] date = input.split(DATE_IDENTIFIER);

        int dayField = Integer.parseInt(date[DAY_FIELD].trim());
        int monthField = Integer.parseInt(date[MONTH_FIELD].trim());
        int yearField = Integer.parseInt(date[YEAR_FIELD].trim());

        LocalDateTime createDate = LocalDateTime.now();

        createDate.withDayOfMonth(dayField);
        createDate.withMonth(monthField);
        createDate.withYear(yearField);

        return createDate;
    }

    /**
     * Splits up the DD/MM/YYYY into day, month, field int components.
     * @param input the date string in DD/MM/YYYY
     * @return an integer array containing the values of individual components.
     */
    public static Integer[] splitComponents(String input) {

        Integer[] dateComponents = new Integer[NUM_COMPONENTS];
        String[] inputDate = input.split(DATE_IDENTIFIER);

        dateComponents[DAY_FIELD] = Integer.parseInt(inputDate[DAY_FIELD].trim());
        dateComponents[MONTH_FIELD] = Integer.parseInt(inputDate[MONTH_FIELD].trim());
        dateComponents[YEAR_FIELD] = Integer.parseInt(inputDate[YEAR_FIELD].trim());

        return dateComponents;
    }

    /**
     * Used to reformat the flexible date input to DD/MM/YYYY
     * @param day day of month
     * @param month month of year
     * @param year year
     * @return Date formatted in DD/MM/YYYY
     */
    public static String generateStringDateFormat(int day, int month, int year) {

        StringBuilder sb = new StringBuilder();
        sb.append(day);
        sb.append(DATE_IDENTIFIER);
        sb.append(month);
        sb.append(DATE_IDENTIFIER);
        sb.append(year);

        return sb.toString().trim();
    }

    /**
     * Getter for the target date.
     * @return the value of the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns a clone of this PocketProjectDate object.
     */
    public PocketProjectDate clone() {
        return new PocketProjectDate(this.date);
    }

    @Override
    public String toString() {
        return this.date;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PocketProjectDate // instanceof handles nulls
                && this.date.equals(((PocketProjectDate) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return this.date.hashCode();
    }
}
