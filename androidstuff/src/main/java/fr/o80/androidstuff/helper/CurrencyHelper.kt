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
fun Double.format(): String =
        NumberFormat.getInstance(Locale.getDefault())
                .apply {
                    minimumFractionDigits = 2
                }
                .format(this)

/**
 * Format an amount of a specific currency.
 */
fun Double.formatAmount(currencyCode: String): String =
        NumberFormat.getCurrencyInstance(Locale.getDefault())
                .apply {
                    currency = Currency.getInstance(currencyCode)
                }
                .format(this)

/**
 * Format an amount of a specific currency in order to use it for accessibility purpose.
 *
 * * €50 -&gt; €50
 * * -€50 -&gt; €-50
 */
fun Double.formatAmountForAccessibility(currencyCode: String): String =
        NumberFormat.getCurrencyInstance(Locale.getDefault())
                .apply {
                    currency = Currency.getInstance(currencyCode)
                }
                .format(this)
                .replace("-([^0-9]+)".toRegex(), "$1-")
