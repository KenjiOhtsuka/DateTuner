package com.improve_future.date_tuner

import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

object DateTuner {
    // <editor-fold defaultstate="collapsed" desc="date-formatter">
    private val slashDateFormatter = SimpleDateFormat("yyyy/MM/dd")

    @JvmStatic
    fun formatToSlashSeparatedDate(date: Date): String {
        return slashDateFormatter.format(date)
    }

    @JvmStatic
    fun formatToNullableSlashSeparatedDate(date: Date?): String? {
        date ?: return null
        return formatToSlashSeparatedDate(date)
    }

    /**
     * Convert LocalDate to String such as "2018/07/29"
     *
     * @param localDate
     */
    @JvmStatic
    fun formatToSlashSeparatedDate(localDate: LocalDate): String {
        return formatToSlashSeparatedDate(
            createDate(localDate)
        )
    }

    /**
     * Convert LocalDate to String such as "2018-07-29".
     *
     * @param localDate
     */
    @JvmStatic
    fun formatToNullableSlashSeparatedDate(localDate: LocalDate?): String? {
        localDate ?: return null
        return formatToSlashSeparatedDate(localDate)
    }

    private val slashFormatterFromYearMonthToYearMonth =
        DateTimeFormatter.ofPattern("yyyy/MM")

    /**
     * Convert YearMonth to String such as "2018/08"
     *
     * @param yearMonth
     */
    @JvmStatic
    fun formatToSlashSeparatedYearMonth(yearMonth: YearMonth): String {
        return yearMonth.format(slashFormatterFromYearMonthToYearMonth)
    }

    private val hyphenDateFormatter = SimpleDateFormat("yyyy-MM-dd")
    /**
     * Convert Date to String such as "2018-07-29"
     *
     * @param date
     */
    @JvmStatic
    fun formatToHyphenSeparatedDate(date: Date): String {
        return hyphenDateFormatter.format(date)
    }

    /**
     * Convert LocalDate to String such as "2018-07-29"
     *
     * @param localDate
     */
    @JvmStatic
    fun formatToHyphenSeparatedDate(localDate: LocalDate): String {
        return formatToHyphenSeparatedDate(
            createDate(localDate)
        )
    }

    /**
     * Convert Date? to String? such as "2018-07-29"
     *
     * @param date
     */
    @JvmStatic
    fun formatToNullableHyphenSeparatedDate(date: Date?): String? {
        if (date == null) return null
        return hyphenDateFormatter.format(date)
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="year-month-formatter">
    private val hyphenFormatterFromYearMonthToYearMonth =
        DateTimeFormatter.ofPattern("yyyy-MM")

    @JvmStatic
    fun formatToHyphenSeparatedYearMonth(yearMonth: YearMonth) =
        yearMonth.format(
            hyphenFormatterFromYearMonthToYearMonth
        )

    @JvmStatic
    fun formatYearMonth(yearMonth: YearMonth, pattern: String): String {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return yearMonth.format(formatter)
    }

    private val hyphenFormatterToYearMonth =
        SimpleDateFormat("yyyy-MM")

    @JvmStatic
    fun formatToHyphenSeparatedYearMonth(date: Date) =
        hyphenFormatterToYearMonth.format(date)
    // </editor-fold>

    // <editor-fold desc="time formatter">
    private val colonFormatterToHourMinute =
        DateTimeFormatter.ofPattern("HH:mm")

    /**
     * Convert LocalTime to String such as "12:34".
     *
     * @param time
     */
    @JvmStatic
    fun formatToColonSeparatedHourMinute(time: LocalTime) =
        colonFormatterToHourMinute.format(time)

    /**
     * Convert LocalTime to String? such as "12:34".
     */
    @JvmStatic
    fun formatToNullableColonSeparatedHourMinute(
        time: LocalTime?
    ): String? {
        if (time == null) return null
        return formatToColonSeparatedHourMinute(time)
    }
    // </editor-fold>

    // <editor-fold desc="datetime formatter">
    private val globalStyleFormatterToDateTime =
        SimpleDateFormat("yyyy-MM-dd hh:mm")

    @JvmStatic
    fun formatToGlobalStyledDateTime(date: Date) =
        globalStyleFormatterToDateTime.format(date)

    @JvmStatic
    fun formatToNullableGlobalStyledDateTime(date: Date?): String? {
        date ?: return null
        return formatToGlobalStyledDateTime(date)
    }
    // </editor-fold>

    // <editor-fold desc="date factory">
    @JvmStatic
    fun createDate(
        year: Int,
        month: Int,
        day: Int
    ): Date {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day)
        return calendar.time
    }

    @JvmStatic
    fun createDate(
        year: Int,
        month: Int,
        day: Int,
        hour: Int,
        minute: Int,
        second: Int
    ): Date {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day, hour, minute, second)
        return calendar.time
    }


    @JvmStatic
    fun createDate(localDate: LocalDate): Date =
        Date.from(
            localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        )

    @JvmStatic
    fun createCurrentDate() = Date()

    /**
     * Create Date that indicate current time on yesterday.
     */
    @JvmStatic
    fun createNowOfYesterday(): Date {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.set(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH) - 1
        )
        return calendar.time
    }

    @JvmStatic
    fun createDateFromSlashedString(dateString: String) =
        slashDateFormatter.parse(dateString)

    @JvmStatic
    fun createNullableDateFromSlashedString(dateString: String?): Date? {
        if (dateString == null) return null
        return createDateFromSlashedString(dateString)
    }

    @JvmStatic
    fun createDateFromHyphenSeparatedString(dateString: String) =
        hyphenDateFormatter.parse(dateString)

    @JvmStatic
    fun createNullableDateFromHyphenSeparatedString(dateString: String?): Date? {
        if (dateString == null) return null
        return createDateFromHyphenSeparatedString(dateString)
    }
    // </editor-fold>

    // <editor-fold desc="year-month factory">
    @JvmStatic
    fun createYearMonth(year: Int, month: Int) =
        YearMonth.of(year, month)

    /**
     * Create YearMonth instance.
     *
     * @param year
     * @param month
     */
    @JvmStatic
    fun createYearMonth(year: Number, month: Number) =
        createYearMonth(year.toInt(), month.toInt())

    /**
     * Create YearMonth instance.
     *
     * @param date
     */
    @JvmStatic
    fun createYearMonth(date: Date = Date()): YearMonth {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return createYearMonth(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1
        )
    }

    /**
     * Create YearMonth from Int such as `201807`.
     *
     * @param yearMonthNumber
     */
    @JvmStatic
    fun createYearMonth(yearMonthNumber: Int) =
        createYearMonth(
            yearMonthNumber / 100,
            yearMonthNumber % 100
        )

    /**
     * Create YearMonth from Long such as `201807`.
     *
     * @param yearMonthNumber
     */
    @JvmStatic
    fun createYearMonth(yearMonthNumber: Long) =
        createYearMonth(
            (yearMonthNumber / 100).toInt(),
            (yearMonthNumber % 100).toInt()
        )

    @JvmStatic
    fun differenceInMonth(
        fromYearMonth: YearMonth,
        toYearMonth: YearMonth
    ) = ((toYearMonth.year - fromYearMonth.year) * 12 +
            toYearMonth.monthValue - fromYearMonth.monthValue).toLong()

    @JvmStatic
    fun createPreviousYearMonth(offset: Long = 1) =
        createYearMonth().minusMonths(offset)

    @JvmStatic
    fun createNextYearMonth(offset: Long = 1) =
        createYearMonth().plusMonths(offset)
    // </editor-fold>

    // <editor-fold desc="time factory">
    @JvmStatic
    fun createTimeFromColonSeparatedString(
        timeString: String
    ) = LocalTime.parse(
        timeString, colonFormatterToHourMinute
    )

    @JvmStatic
    fun createNullableTimeFromColonSeparatedString(
        timeString: String?
    ): LocalTime? {
        if (timeString == null) return null
        return createTimeFromColonSeparatedString(timeString)
    }
    // </editor-fold>

    /**
     * Create Year instance that indicates current year.
     */
    @JvmStatic
    fun createCurrentYear() = Year.now()

    @JvmStatic
    fun getMonth(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.YEAR)
    }

    @JvmStatic
    fun getMonth(yearMonth: YearMonth): Int = yearMonth.month.value

    @JvmStatic
    fun getYear(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.MONTH) + 1
    }

    @JvmStatic
    fun getYear(yearMonth: YearMonth): Int = yearMonth.year

    @JvmStatic
    fun getDay(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.DAY_OF_MONTH)
    }
}
