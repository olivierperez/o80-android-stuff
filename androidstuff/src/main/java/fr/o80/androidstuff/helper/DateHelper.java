package fr.o80.androidstuff.helper;

import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public final class DateHelper {


    private DateHelper() {
    }

    public static String dateToTimestamp(Date date) {
        return Long.toString(date.getTime() / 1000);
    }

    public static Date strToDate(String dateString) {
        if (dateString == null) {
            return null;
        }

        try {
            if (dateString.matches("^[0-9]+$")) {
                return new Date(Long.parseLong(dateString) * 1000);

            } else if (dateString.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
                return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateString);

            } else if (dateString.matches("^[0-9]{8}$")) {
                return new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).parse(dateString);

            } else {
                return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH).parse(dateString);
            }
        } catch (ParseException e) {
            Timber.e(e, "Cannot parse date '%s'", dateString);
            return null;
        }
    }

    public static String getDateFormatted(Date date, String format) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(date);
    }

    public static Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    private static boolean isDateToday(Date dateToCheck) {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        Date today = c.getTime();
        return dateToCheck.after(today);
    }

    private static boolean areSameDay(Calendar calendarMod, Calendar calendarToCheck) {
        return calendarMod.get(Calendar.YEAR) == calendarToCheck.get(Calendar.YEAR)
                && calendarMod.get(Calendar.DAY_OF_YEAR) == calendarToCheck.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Check is {@code monthToCheck} is stricly before {@code reference}.
     * @param monthToCheck The month that must be before
     * @param reference The month that must be after
     */
    public static boolean monthIsBefore(Calendar monthToCheck, Calendar reference) {
        return monthToCheck.get(Calendar.YEAR) < reference.get(Calendar.YEAR)
                || monthToCheck.get(Calendar.MONTH) < reference.get(Calendar.MONTH);
    }

}
