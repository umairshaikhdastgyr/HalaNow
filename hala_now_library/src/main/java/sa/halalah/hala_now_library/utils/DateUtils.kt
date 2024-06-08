package sa.halalah.hala_now_library.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateUtils {
    fun getDateString(date: Date?): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return date?.let { simpleDateFormat.format(it) } ?: ""
    }

    fun formatDate(inputString: String, format: String = "dd MMM, yyyy"): String {
        var dateValue = inputString
        val lastDotIndex = dateValue.lastIndexOf('.')
        if (lastDotIndex != -1) {
            dateValue = dateValue.substring(0, lastDotIndex)
        }

        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputDateFormat = SimpleDateFormat(format, Locale.getDefault())
        val inputDate = inputDateFormat.parse(dateValue)
        return outputDateFormat.format(inputDate!!)
    }
}