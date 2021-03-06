package com.realitix.mealassistant.util

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

class MealUtil {
    companion object {
        private const val nbSecondsInMinute: Long = 60
        private const val nbSecondsInHour: Long = nbSecondsInMinute*60
        private const val nbSecondsInDay: Long = nbSecondsInHour*24
        private val pattern1 = initSimpleDate("EEEE d MMMM")
        private val pattern2 = initSimpleDate("HH:mm")
        private val pattern3 = initSimpleDate("EEEE d MMMM YYYY")

        fun beginDayTimestamp(timestamp: Long): Long = floor((timestamp / nbSecondsInDay).toDouble()).toLong() * nbSecondsInDay
        fun endDayTimestamp(timestamp: Long): Long = beginDayTimestamp(timestamp) + nbSecondsInDay
        fun hourTimestamp(hour: Int): Long = hour * nbSecondsInHour
        fun minuteTimestamp(minute: Int): Long = minute * nbSecondsInMinute
        fun dayMonthFromTimestamp(timestamp: Long): String = pattern1.format(Date(timestamp*1000))
        fun dayMonthYearFromTimestamp(timestamp: Long): String = pattern3.format(Date(timestamp*1000))
        fun hourMinuteFromTimestamp(timestamp: Long): String = pattern2.format(Date(timestamp*1000))

        private fun initSimpleDate(pattern: String): SimpleDateFormat {
            val r = SimpleDateFormat(pattern, Locale.getDefault())
            r.timeZone = TimeZone.getTimeZone("GMT")
            return r
        }
    }
}