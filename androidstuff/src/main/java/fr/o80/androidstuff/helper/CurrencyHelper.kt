@file:JvmName("CurrencyHelper")

package fr.o80.androidstuff.helper

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

/**
 * Format a number to be displayed with only 2 decimals.
 *
 *  * 0.1289 -&gt; 0.13
 *  * 15.6 -&gt; 15.60
 *  * [US] 1515.6 -&gt; 1,515.60
 *  * [FR] 1515.6 -&gt; 1 515,60
 *

 * @param value The number to format
 * *
 * @return The number in string with 2 decimals
 */
fun Double.format(): String {
    val formatter = NumberFormat.getInstance(Locale.getDefault())
    formatter.minimumFractionDigits = 2
    return formatter.format(this)
}

fun Double.format(currencyCode: String): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.getDefault())
    formatter.currency = Currency.getInstance(currencyCode)
    return formatter.format(this)
}

fun Double.accessibilityFormat(currencyCode: String): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.getDefault())
    formatter.currency = Currency.getInstance(currencyCode)
    val value = formatter.format(this)
    return value.replace("-([^0-9]+)".toRegex(), "$1-")
}

fun String.removeCurrencySign(currency: Currency): String =
        if (this.contains(currency.symbol)) {
            replace(currency.symbol, "")
        } else {
            this
        }
