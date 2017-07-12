package fr.o80.androidstuff.helper;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public final class CurrencyHelper {

    private CurrencyHelper() {
    }

    /**
     * Format a number to be displayed with only 2 decimals.
     * <ul>
     *     <li>0.1289 -&gt; 0.13</li>
     *     <li>15.6 -&gt; 15.60</li>
     *     <li>[US] 1515.6 -&gt; 1,515.60</li>
     *     <li>[FR] 1515.6 -&gt; 1 515,60</li>
     * </ul>
     *
     * @param value The number to format
     * @return The number in string with 2 decimals
     */
    public static String format(double value) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.getDefault());
        formatter.setMinimumFractionDigits(2);
        return formatter.format(value);
    }

    public static String format(double amount, String currencyCode) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
        formatter.setCurrency(Currency.getInstance(currencyCode));
        return formatter.format(amount);
    }

    public static String accessibilityFormat(double amount, String currencyCode) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
        formatter.setCurrency(Currency.getInstance(currencyCode));
        String value = formatter.format(amount);
        return value.replaceAll("-([^0-9]+)", "$1-");
    }

    public static String removeCurrencySign(String amount, Currency currency) {
        if (amount.contains(currency.getSymbol())) {
            amount = amount.replace(currency.getSymbol(), "");
        }
        return amount;
    }

}
