package seedu.address.model.project;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The date used in the pocket project that allows flexible entries.
 */

public class FlexibleDate {

    private LocalDateTime targetDate;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final int LENGTH_OF_WEEK = 7;
    private static final int NEXT = 1;
    private static final int LAST = -1;

    public FlexibleDate() {
        this.targetDate = LocalDateTime.now();
    }

    public String currentDate() {
        return DATE_FORMAT.format(targetDate);
    }

    public String dateNumDaysLater(long numDays) {
        LocalDateTime newDate = targetDate.plusDays(numDays);
        return DATE_FORMAT.format(newDate);
    }

    public String dateNumDaysBefore(long numDays) {
        LocalDateTime newDate = targetDate.minusDays(numDays);
        return DATE_FORMAT.format(newDate);
    }

    public String dateNumWeeksLater(long numWeeks) {
        LocalDateTime newDate = targetDate.plusWeeks(numWeeks);
        return DATE_FORMAT.format(newDate);
    }

    public String dateNumWeeksBefore(long numWeeks) {
        LocalDateTime newDate = targetDate.minusWeeks(numWeeks);
        return DATE_FORMAT.format(newDate);
    }

    public String dateNumMonthsLater(long numMonths) {
        LocalDateTime newDate = targetDate.plusMonths(numMonths);
        return DATE_FORMAT.format(newDate);
    }

    public String dateNumMonthsBefore(long numMonths) {
        LocalDateTime newDate = targetDate.minusMonths(numMonths);
        return DATE_FORMAT.format(newDate);
    }

    public String dateNumYearsLater(long numYears) {
        LocalDateTime newDate = targetDate.plusYears(numYears);
        return DATE_FORMAT.format(newDate);
    }

    public String dateNumYearsBefore(long numYears) {
        LocalDateTime newDate = targetDate.minusYears(numYears);
        return DATE_FORMAT.format(newDate);
    }

    public String thisWeekDate(int targetDayOfWeek) {
        int currentDayOfWeek = LocalDateTime.now().getDayOfWeek().getValue();
        LocalDateTime newDate = targetDate.plusDays(targetDayOfWeek - currentDayOfWeek);
        return DATE_FORMAT.format(newDate);
    }

    public String thisMonthDate(int targetDayOfMonth) {
        int currentDayOfMonth = LocalDateTime.now().getDayOfMonth();
        LocalDateTime newDate = targetDate.plusDays(targetDayOfMonth - currentDayOfMonth);
        return DATE_FORMAT.format(newDate);
    }

    public String nextWeekDate(int targetDayOfWeek) {
        int currentDayOfWeek = LocalDateTime.now().getDayOfWeek().getValue();
        LocalDateTime newDate = targetDate.plusDays(LENGTH_OF_WEEK);
        return DATE_FORMAT.format(newDate.plusDays(targetDayOfWeek - currentDayOfWeek));
    }

    public String nextMonthDate(int targetDayOfMonth) {
        int currentMonth = LocalDateTime.now().getMonth().getValue();
        int currentDayOfMonth = LocalDateTime.now().getDayOfMonth();
        LocalDateTime targetDate = LocalDateTime.now().withMonth(currentMonth + NEXT);
        LocalDateTime newDate = targetDate.plusDays(targetDayOfMonth - currentDayOfMonth);
        return DATE_FORMAT.format(newDate);
    }

    public LocalDateTime getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDateTime targetDate) {
        this.targetDate = targetDate;
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
