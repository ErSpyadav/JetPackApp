package extension

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.NumberFormat.*
import java.util.*

fun Double.toCurrencyFormat(currency: String = "£", symbol: String? = null): String {
    val dfs =
        DecimalFormatSymbols() // we do not request the ES local, because it may not be present on the device.
    dfs.decimalSeparator = '.'
    dfs.groupingSeparator = ','
    val decimalFormat = DecimalFormat("#,##0.00", dfs)
    decimalFormat.isGroupingUsed = true
    decimalFormat.minimumFractionDigits = 2

    val formatted = decimalFormat.format(this).replace("-", "")

    return if (symbol.isNullOrBlank()) {
        if (this < 0f) "- $currency$formatted" else "$currency$formatted"
    } else {
        "$symbol $currency$formatted"
    }
}

fun Double.toTwoDecimalsFormat(): String {
    val numberFormat = getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    return numberFormat.format(this)
}
