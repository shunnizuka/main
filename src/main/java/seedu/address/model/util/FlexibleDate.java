package seedu.address.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The date used in the pocket project that allows flexible entries.
 */

public class FlexibleDate {

    private LocalDateTime targetDate;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static final String DAY_OF_WEEK_MONTH_CONSTRAINTS = "The day of week has to be an integer between 1 and 7 "
        + "inclusive and the day of month has to be an integer between 1 and 31 and correspond to the max number of "
        + "days in particular month";

    public static final String FLEXI_DATE_MESSAGE_CONSTRAINTS = "Flexible date inputs only allow choosing between "
        + "this/next/last week or month. They keyword week/month has to be included";

    private static final int LAST = -1;
    private static final int LENGTH_OF_WEEK = 7;
    private static final int NEXT = 1;

    /**
     * Constructor
     */
    public FlexibleDate() {
        this.targetDate = LocalDateTime.now();
    }

    /**
     * Method that returns the current date based on local time zone.
     * @return current date according to given format
     */
    public String currentDate() {
        return DATE_FORMAT.format(targetDate);
    }

    /**
     * Method that adds specified number of days to current date.
     * @param numDays number of days to be added.
     * @return target date according to given format
     */
    public String dateNumDaysLater(long numDays) {
        LocalDateTime newDate = targetDate.plusDays(numDays);
        return DATE_FORMAT.format(newDate);
    }

    /**
     * Method that subtracts specified number of days from current date.
     * @param numDays number of days to be subtracted.
     * @return target date according to given format
     */
    public String dateNumDaysBefore(long numDays) {
        LocalDateTime newDate = targetDate.minusDays(numDays);
        return DATE_FORMAT.format(newDate);
    }

    /**
     * Method that adds specified number of weeks to current date.
     * @param numWeeks number of weeks to be added.
     * @return target date according to given format
     */
    public String dateNumWeeksLater(long numWeeks) {
        LocalDateTime newDate = targetDate.plusWeeks(numWeeks);
        return DATE_FORMAT.format(newDate);
    }
    /**
     * Method that subtracts specified number of weeks from current date.
     * @param numWeeks number of weeks to be subtracted.
     * @return target date according to given format
     */
    public String dateNumWeeksBefore(long numWeeks) {
        LocalDateTime newDate = targetDate.minusWeeks(numWeeks);
        return DATE_FORMAT.format(newDate);
    }

    /**
     * Method that adds specified number of months to current date.
     * @param numMonths number of weeks to be added.
     * @return target date according to given format
     */
    public String dateNumMonthsLater(long numMonths) {
        LocalDateTime newDate = targetDate.plusMonths(numMonths);
        return DATE_FORMAT.format(newDate);
    }

    /**
     * Method that subtracts specified number of months from current date.
     * @param numMonths number of weeks to be subtracted.
     * @return target date according to given format
     */
    public String dateNumMonthsBefore(long numMonths) {
        LocalDateTime newDate = targetDate.minusMonths(numMonths);
        return DATE_FORMAT.format(newDate);
    }

    /**
     * Method that adds specified number of years to current date.
     * @param numYears number of weeks to be added.
     * @return target date according to given format
     */
    public String dateNumYearsLater(long numYears) {
        LocalDateTime newDate = targetDate.plusYears(numYears);
        return DATE_FORMAT.format(newDate);
    }

    /**
     * Method that subtracts specified number of years from current date.
     * @param numYears number of weeks to be subtracted.
     * @return target date according to given format
     */
    public String dateNumYearsBefore(long numYears) {
        LocalDateTime newDate = targetDate.minusYears(numYears);
        return DATE_FORMAT.format(newDate);
    }

    /**
     * Method that calculated date of this week using target day.
     * @param targetDayOfWeek target day of the week
     * @return target date according to given format
     */
    public String thisWeekDate(int targetDayOfWeek) {
        int currentDayOfWeek = LocalDateTime.now().getDayOfWeek().getValue();
        LocalDateTime newDate = targetDate.plusDays(targetDayOfWeek - currentDayOfWeek);
        return DATE_FORMAT.format(newDate);
    }

    /**
     * Method that calculated date of this month using target day.
     * @param targetDayOfMonth target day of the month
     * @return target date according to given format
     */
    public String thisMonthDate(int targetDayOfMonth) {
        int currentDayOfMonth = LocalDateTime.now().getDayOfMonth();
        LocalDateTime newDate = targetDate.plusDays(targetDayOfMonth - currentDayOfMonth);
        return DATE_FORMAT.format(newDate);
    }

    /**
     * Method that calculated date of next week using target day.
     * @param targetDayOfWeek target day of the week
     * @return target date according to given format
     */
    public String nextWeekDate(int targetDayOfWeek) {
        int currentDayOfWeek = LocalDateTime.now().getDayOfWeek().getValue();
        LocalDateTime newDate = targetDate.plusDays(LENGTH_OF_WEEK);
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
        LocalDateTime newDate = targetDate.plusDays(targetDayOfMonth - currentDayOfMonth + NEXT);
        return DATE_FORMAT.format(newDate);
    }

    /**
     * Method that calculated date of last week using target day.
     * @param targetDayOfWeek target day of the week
     * @return target date according to given format
     */
    public String lastWeekDate(int targetDayOfWeek) {
        int currentDayOfWeek = LocalDateTime.now().getDayOfWeek().getValue();
        LocalDateTime newDate = targetDate.minusDays(LENGTH_OF_WEEK);
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
        LocalDateTime newDate = targetDate.plusDays(targetDayOfMonth - currentDayOfMonth + NEXT);
        return DATE_FORMAT.format(newDate);
    }

    /**
     * Returns a clone of this FlexibleDate object.
     */
    public FlexibleDate clone() {
        return new FlexibleDate();
    }

    @Override
    public String toString() {
        return DATE_FORMAT.format(this.targetDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlexibleDate // instanceof handles nulls
                && this.targetDate.equals(((FlexibleDate) other).targetDate)); // state check
    }

    @Override
    public int hashCode() {
        return this.targetDate.hashCode();
    }
}
