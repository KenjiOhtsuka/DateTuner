package com.improve_future.date_tuner

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.YearMonth
import java.util.*

class DateTunerTest {
    @Test
    fun testCreateDate() {
        val date = DateTuner.createDate(2000, 1, 2)
        val calendar = Calendar.getInstance()
        calendar.time = date
        assertEquals(2000, calendar.get(Calendar.YEAR))
        assertEquals(0, calendar.get(Calendar.MONTH))
        assertEquals(2, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun testFormatToSlashSeparatedDate() {
        val date = DateTuner.createDate(2000, 1, 2)
        assertEquals(
                "2000/01/02",
                DateTuner.formatToSlashSeparatedDate(date))
        val localDate = DateTuner.createYearMonth(2017, 1).atDay(1)
        assertEquals(
                "2017/01/01",
            DateTuner.formatToSlashSeparatedDate(localDate))
    }

    @Test
    fun testDifferenceInMonth() {
        var fromYearMonth = DateTuner.createYearMonth(2014, 6)
        var toYearMonth = DateTuner.createYearMonth(2016, 12)
        assertEquals(
                30,
                DateTuner.differenceInMonth(fromYearMonth, toYearMonth))
        assertEquals(
                -30,
                DateTuner.differenceInMonth(toYearMonth, fromYearMonth))
        fromYearMonth = DateTuner.createYearMonth(2015, 9)
        toYearMonth = DateTuner.createYearMonth(2017, 3)
        assertEquals(
                18,
                DateTuner.differenceInMonth(fromYearMonth, toYearMonth))
        assertEquals(
                -18,
                DateTuner.differenceInMonth(toYearMonth, fromYearMonth))
    }
}