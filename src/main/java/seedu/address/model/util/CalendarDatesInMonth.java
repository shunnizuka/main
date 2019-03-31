package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;

import seedu.address.logic.parser.CliSyntax;

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
     private static final int FIRST_DAY_MONTH = 1;
     private static final int LAST_MONTH = 12;
     private static final int NEXT = 1;

    /**
     * Message constraints.
     */
    public static final String DAY_MONTH_CONSTRAINTS = "The selected month does not have the chosen day or "
        + "the selected month does not exist in the calendar.";

    public static final String DATE_IDENTIFIER = "/";

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

    public static boolean isValidDayInMonth (String dateInput) {

        String[] date = dateInput.split(DATE_IDENTIFIER);

        int dayField = Integer.parseInt(date[DAY_FIELD].trim());
        int monthField = Integer.parseInt(date[MONTH_FIELD].trim());
        int yearField = Integer.parseInt(date[YEAR_FIELD].trim());

        if (monthField < FIRST_DAY_MONTH || monthField > LAST_MONTH) {
            return false;
        }

        int maxDays = daysInMonth.get(monthField);
        if(dayField < FIRST_DAY_MONTH || dayField > maxDays) {
            return false;
        }

        if(monthField == FEBRUARY && dayField == FEB_MAX_DAYS) {
            if(!isLeapYear(yearField)) {
                return false;
            }
        }

        return true;
    }

     /**
     * This method checks whether a year is leap or not by checking the number of days in the
     * year exceed 365 days. Leap years have 366 days in a year.
     *
     * @param year the target year.
     * @return true is leap year and false otherwise.
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

    /**
     * Used to reformat the flexible date input to DD/MM/YYYY so that input can be checked to see
     * if it is a valid day in the month.
     * @param keyword indicates whether this, next or last month.
     * @param dayOfMonth the target day of the month.
     *
     * @return if it is a valid day in the selected month.
     */
     public static boolean doesMonthContainDay(String keyword, int dayOfMonth) {

         LocalDateTime localDateTime = LocalDateTime.now();
         int currentYear = localDateTime.getYear();
         int currentMonth = localDateTime.getMonth().getValue();

         if(keyword.equals(CliSyntax.PREFIX_CURRENT.toString())) {
            return isValidDayInMonth(generateDateFormat(dayOfMonth, currentMonth, currentYear));
         } else if (keyword.equals(CliSyntax.PREFIX_FUTURE.toString())) {
            return  isValidDayInMonth(generateDateFormat(dayOfMonth, (currentMonth + NEXT) % LAST_MONTH,
                currentYear));
         } else {
            return  isValidDayInMonth(generateDateFormat(dayOfMonth, (currentMonth - NEXT) % LAST_MONTH,
                currentYear));
         }
     }

    /**
     * Used to reformat the flexible date input to DD/MM/YYYY
     * @param day day of month
     * @param month month of year
     * @param year year
     * @return Date formatted in DD/MM/YYYY
     */
     private static String generateDateFormat(int day, int month, int year) {

        StringBuilder sb = new StringBuilder();
        sb.append(day);
        sb.append(DATE_IDENTIFIER);
        sb.append(month);
        sb.append(DATE_IDENTIFIER);
        sb.append(year);

        return sb.toString().trim();
     }

}
