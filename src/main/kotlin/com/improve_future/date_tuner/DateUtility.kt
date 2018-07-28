package com.improve_future.date_tuner

import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtility {
    // <editor-fold defaultstate="collapsed" desc="date-formatter">
    private val slashDateFormatter = SimpleDateFormat("yyyy/MM/dd")
    fun formatToSlashSeparatedDate(date: Date) : String {
        return slashDateFormatter.format(date)
    }
    fun formatToNullableSlashSeparatedDate(date: Date?): String? {
        date ?: return null
        return formatToSlashSeparatedDate(date)
    }

    fun formatToSlashSeparatedDate(localDate: LocalDate): String {
        return formatToSlashSeparatedDate(
                createDate(localDate))
    }
    fun formatToNullableSlashSeparatedDate(localDate: LocalDate?): String? {
        localDate ?: return null
        return formatToSlashSeparatedDate(localDate)
    }

    private val slashFormatterFromYearMonthToYearMonth =
            DateTimeFormatter.ofPattern("yyyy/MM")
    fun formatToSlashSeparatedYearMonth(yearMonth: YearMonth) : String {
        return yearMonth.format(slashFormatterFromYearMonthToYearMonth)
    }

    private val hyphenDateFormatter = SimpleDateFormat("yyyy-MM-dd")
    fun formatToHyphenSeparatedDate(date: Date): String {
        return hyphenDateFormatter.format(date)
    }
    fun formatToHyphenSeparatedDate(localDate: LocalDate): String {
        return formatToHyphenSeparatedDate(
                createDate(localDate))
    }

    fun formatToNullableHyphenSeparatedDate(date: Date?): String? {
        if (date == null) return null
        return hyphenDateFormatter.format(date)
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="year-month-formatter">
    private val hyphenFormatterFromYearMonthToYearMonth =
            DateTimeFormatter.ofPattern("yyyy-MM")
    fun formatToHyphenSeparatedYearMonth(yearMonth: YearMonth): String {
        return yearMonth.format(
                hyphenFormatterFromYearMonthToYearMonth)
    }

    fun formatYearMonth(yearMonth: YearMonth, pattern: String): String {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return yearMonth.format(formatter)
    }

    private val hyphenFormatterToYearMonth =
        SimpleDateFormat("yyyy-MM")
    fun formatToHyphenSeparatedYearMonth(date: Date): String {
        return hyphenFormatterToYearMonth.format(date)
    }
    // </editor-fold>

    // <editor-fold desc="time formatter">
    private val colonFormatterToHourMinute =
        DateTimeFormatter.ofPattern("HH:mm")
    fun formatToColonSeparatedHourMinute(time: LocalTime): String {
        return colonFormatterToHourMinute.format(time)
    }

    fun formatToNullableColonSeparatedHourMinute(
            time: LocalTime?): String? {
        if (time == null) return null
        return formatToColonSeparatedHourMinute(time)
    }
    // </editor-fold>

    // <editor-fold desc="datetime formatter">
    private val globalStyleFormatterToDateTime =
            SimpleDateFormat("yyyy-MM-dd hh:mm")
    fun formatToGlobalStyledDateTime(date: Date): String {
        return globalStyleFormatterToDateTime.format(date)
    }
    fun formatToNullableGlobalStyledDateTime(date: Date?): String? {
        date ?: return null
        return formatToGlobalStyledDateTime(date)
    }
    // </editor-fold>

    // <editor-fold desc="date factory">
    fun createDate(year: Int, month: Int, day: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day)
        return calendar.time
    }

    fun createDate(localDate: LocalDate): Date {
        return Date.from(localDate.
                atStartOfDay(ZoneId.systemDefault()).toInstant())
    }

    fun createCurrentDate(): Date {
        return Date()
    }

    fun createNowOfYesterday(): Date {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH) - 1)
        return calendar.time
    }

    fun createDateFromSlashedString(dateString: String): Date {
        return slashDateFormatter.parse(dateString)
    }

    fun createNullableDateFromSlashedString(dateString: String?): Date? {
        if (dateString == null) return null
        return createDateFromSlashedString(dateString)
    }

    fun createDateFromHyphenSeparatedString(dateString: String): Date {
        return hyphenDateFormatter.parse(dateString)
    }

    fun createNullableDateFromHyphenSeparatedString(dateString: String?): Date? {
        if (dateString == null) return null
        return createDateFromHyphenSeparatedString(dateString)
    }
    // </editor-fold>

    // <editor-fold desc="year-month factory">
    fun createYearMonth(year: Int, month: Int): YearMonth {
        return YearMonth.of(year, month)
    }

    fun createYearMonth(year: Number, month: Number): YearMonth {
        return createYearMonth(year.toInt(), month.toInt())
    }

    fun createYearMonth(date: Date = Date()): YearMonth {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return createYearMonth(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1)
    }

    fun createYearMonth(yearMonthNumber: Int): YearMonth {
        return createYearMonth(
                yearMonthNumber / 100,
                yearMonthNumber % 100)
    }

    fun createYearMonth(yearMonthNumber: Long): YearMonth {
        return createYearMonth(
                (yearMonthNumber / 100).toInt(),
                (yearMonthNumber % 100).toInt())
    }

    fun differenceInMonth(fromYearMonth: YearMonth, toYearMonth: YearMonth): Long {
        return ((toYearMonth.year - fromYearMonth.year) * 12 +
                toYearMonth.monthValue - fromYearMonth.monthValue).toLong()
    }

    fun createPreviousYearMonth(offset: Long = 1): YearMonth {
        return createYearMonth().minusMonths(offset)
    }

    fun createNextYearMonth(offset: Long = 1): YearMonth {
        return createYearMonth().plusMonths(offset)
    }
    // </editor-fold>

    // <editor-fold desc="time factory">
    fun createTimeFromColonSeparatedString(
            timeString: String): LocalTime {
        return LocalTime.parse(
                timeString, colonFormatterToHourMinute)
    }

    fun createNullableTimeFromColonSeparatedString(
            timeString: String?): LocalTime? {
        if (timeString == null) return null
        return createTimeFromColonSeparatedString(timeString)
    }
    // </editor-fold>

    fun createCurrentYear(): Year {
        return Year.now()
    }
}