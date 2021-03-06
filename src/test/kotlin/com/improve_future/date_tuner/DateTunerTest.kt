/*
 * DateTuner
 * Copyright (C) 2019  Kenji Otsuka
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.improve_future.date_tuner

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.YearMonth
import java.util.*

class DateTunerTest {
    @Test
    fun testCreateDate() {
        var date = DateTuner.createDate(2000, 1, 2)
        val calendar = Calendar.getInstance()
        calendar.time = date
        assertEquals(2000, calendar.get(Calendar.YEAR))
        assertEquals(0, calendar.get(Calendar.MONTH))
        assertEquals(2, calendar.get(Calendar.DAY_OF_MONTH))

        date = DateTuner.createDate(
            2000, 2, 1, 14, 5, 6)
        calendar.time = date
        assertEquals(2000, calendar.get(Calendar.YEAR))
        assertEquals(1, calendar.get(Calendar.MONTH))
        assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH))
        assertEquals(14, calendar.get(Calendar.HOUR_OF_DAY))
        assertEquals(5, calendar.get(Calendar.MINUTE))
        assertEquals(6, calendar.get(Calendar.SECOND))

        date = DateTuner.createDate(201205)
        assertEquals(
            "2012-05-01 00:00:00",
            DateTuner.formatToGlobalStyleDateTimeWithSecond(date)
        )
    }

    @Test
    fun testFormatGlobalStyleDateTime() {
        val date = DateTuner.createDate(2000, 1, 2, 13, 4, 5)
        assertEquals(
            "2000-01-02 13:04",
            DateTuner.formatToGlobalStyleDateTime(date)
        )
    }

    @Test
    fun testFormatGlobalStyleDateTimeWithSecond() {
        val date = DateTuner.createDate(2000, 1, 2, 13, 4, 5)
        assertEquals(
            "2000-01-02 13:04:05",
            DateTuner.formatToGlobalStyleDateTimeWithSecond(date)
        )
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

    @Test
    fun testConvertToIntYearMonth() {
        val yearMonth = YearMonth.of(2019, 1)
        assertEquals(201901, DateTuner.convertToIntYearMonth(yearMonth))
    }
}
