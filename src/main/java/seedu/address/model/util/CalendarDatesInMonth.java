package seedu.address.model.util;

import seedu.address.logic.parser.exceptions.ParseException;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Contains methods for checking if a input date is valid according to the calendar year.
 */
public class CalendarDatesInMonth {

    /**
     * HashMap mapping the key(month) to the value (number of days in the month).
     */
    private static HashMap<Integer, Integer> daysInMonth;

    /**
     * Month names and days in each month.
     */
     private static final int JANUARY = 1;
     private static final int JAN_MAX_DAYS = 31;
     private static final int FEBRUARY = 2;
     private static final int FEB_MAX_DAYS = 29;
     private static final int MARCH = 3;
     private static final int MAR_MAX_DAYS = 31;
     private static final int APRIL = 4;
     private static final int APR_MAX_DAYS = 30;
     private static final int MAY = 5;
     private static final int MAY_MAX_DAYS = 31;
     private static final int JUNE = 6;
     private static final int JUNE_MAX_DAYS = 30;
     private static final int JULY = 7;
     private static final int JUL_MAX_DAYS = 31;
     private static final int AUGUST = 8;
     private static final int AUG_MAX_DAYS = 31;
     private static final int SEPTEMBER = 9;
     private static final int SEP_MAX_DAYS = 30;
     private static final int OCTOBER = 10;
     private static final int OCT_MAX_DAYS = 31;
     private static final int NOVEMBER = 11;
     private static final int NOV_MAX_DAYS = 30;
     private static final int DECEMBER = 12;
     private static final int DEC_MAX_DAYS = 31;

    /**
     * Constants to represent different fields.
     */
     private static final int DAY_FIELD = 0;
     private static final int MONTH_FIELD = 1;
     private static final int YEAR_FIELD = 2;
     private static final int FIRST_MONTH = 1;
     private static final int LAST_MONTH = 12;

    /**
     * Message constraints.
     */
    private static final String DAY_MONTH_CONSTRAINTS = "The selected month does not have the chosen day.";
    private static final String MONTH_CONSTRAINTS = "The selected month does not exist in the calendar.";

    /**
     * Static initializer for Calendar Dates In Month
     */
    static {
        daysInMonth = new HashMap<>();

        daysInMonth.put(JANUARY, JAN_MAX_DAYS);
        daysInMonth.put(FEBRUARY, FEB_MAX_DAYS);
        daysInMonth.put(MARCH, MAR_MAX_DAYS);
        daysInMonth.put(APRIL, APR_MAX_DAYS);
        daysInMonth.put(MAY, MAY_MAX_DAYS);
        daysInMonth.put(JUNE, JUNE_MAX_DAYS);
        daysInMonth.put(JULY, JUL_MAX_DAYS);
        daysInMonth.put(AUGUST, AUG_MAX_DAYS);
        daysInMonth.put(SEPTEMBER, SEP_MAX_DAYS);
        daysInMonth.put(OCTOBER, OCT_MAX_DAYS);
        daysInMonth.put(NOVEMBER, NOV_MAX_DAYS);
        daysInMonth.put(DECEMBER, DEC_MAX_DAYS);
    }

    public static boolean isValidDayInMonth (String dateInput) throws Exception {

        String[] date = dateInput.split("/");

        int dayField = Integer.parseInt(date[DAY_FIELD].trim());
        int monthField = Integer.parseInt(date[MONTH_FIELD].trim());
        int yearField = Integer.parseInt(date[YEAR_FIELD].trim());

        if (monthField < FIRST_MONTH || monthField > LAST_MONTH) {
            throw new ParseException(MONTH_CONSTRAINTS);
        }

        int maxDays = daysInMonth.get(monthField);
        if(dayField > maxDays) {
            throw new ParseException(DAY_MONTH_CONSTRAINTS);
        }

        if(monthField == FEBRUARY && dayField == FEB_MAX_DAYS) {
            if(!isLeapYear(yearField)) {
                throw new ParseException(DAY_MONTH_CONSTRAINTS);
            }
        }

        return true;
    }

     /**
     * This method checks whether a year is leap or not by checking the number of days in the
     * year exceed 365 days. Leap years have 366 days in a year.
     */
   public static boolean isLeapYear(int year){
       Calendar calendar = Calendar.getInstance();
       calendar.set(Calendar.YEAR, year);

       int numOfDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);

       if(numOfDays > 365){
           return true;
       }
       return false;
   }

}
