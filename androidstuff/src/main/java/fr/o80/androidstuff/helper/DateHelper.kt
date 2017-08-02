@file:JvmName("DateHelper")

package fr.o80.androidstuff.helper

import android.widget.DatePicker

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

import timber.log.Timber

fun Date.toTimestamp(): Long = time / 1000

fun String.strToDate(): Date =
        when {
            matches("^[0-9]+$".toRegex()) -> Date(java.lang.Long.parseLong(this) * 1000)
            matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$".toRegex()) -> SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
            matches("^[0-9]{8}$".toRegex()) -> SimpleDateFormat("yyyyMMdd", Locale.getDefault()).parse(this)
            else -> SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH).parse(this)
        }

fun Date.getDateFormatted(format: String): String = SimpleDateFormat(format, Locale.getDefault()).format(this)

fun DatePicker.getDateFromDatePicker(): Date =
        Calendar.getInstance()
                .apply { set(year, month, dayOfMonth) }
                .time

fun Date.isDateToday(): Boolean {
    val today = Calendar.getInstance()
    val toCheck = Calendar.getInstance()
            .apply { time = this@isDateToday }
    return toCheck.sameDayAs(today)
}

fun Calendar.sameDayAs(other: Calendar): Boolean {
    return this.get(Calendar.YEAR) == other.get(Calendar.YEAR)
            && this.get(Calendar.DAY_OF_YEAR) == other.get(Calendar.DAY_OF_YEAR)
}

/**
 * Check if Calendar is stricly before `reference`.
 * @param reference The month that must be after
 */
fun Calendar.monthIsBefore(reference: Calendar): Boolean {
    return get(Calendar.YEAR) < reference.get(Calendar.YEAR)
            || get(Calendar.YEAR) == reference.get(Calendar.YEAR) && get(Calendar.MONTH) < reference.get(Calendar.MONTH)
}
