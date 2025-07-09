package com.nickstamp.kit.core.helpers.impl

import com.nickstamp.kit.core.helpers.DateTimeHelper
import kotlinx.datetime.*
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlin.time.Clock
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class DateTimeHelperImpl : DateTimeHelper {

    object Formats {
        const val BACKEND_TIMESTAMP_EPG: String = "yyyy-MM-dd HH:mm:ss"
        const val UI_DAY: String = "EEE dd"
        private const val UI_DATE: String = "E dd MMM"
        const val UI_TIME: String = "HH:mm"
        const val UI_DAY_NAME: String = "EEEE"
        const val UI_TIMESTAMP: String = "$UI_DATE, $UI_TIME"
    }

    object TimezoneId {
        const val GREECE = "Europe/Athens"
    }

    private val hourOfDayConsideredNewDay = 5

    override fun minutesToMilliseconds(minutes: Int): Long {
        return minutes.minutes.inWholeMilliseconds
    }

    override fun getCurrentTimeInMillis(): Long {
        return Clock.System.now().toEpochMilliseconds()
    }

    @OptIn(FormatStringsInDatetimeFormats::class)
    override fun getLocalTimeInMillisFromEpgBackendFormat(dateTimeStr: String): Long {
        return try {
            val formatter = LocalDateTime.Format {
                byUnicodePattern(Formats.BACKEND_TIMESTAMP_EPG)
            }
            val localDateTime = formatter.parse(dateTimeStr)
            localDateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
        } catch (t: Throwable) {
            0L
        }
    }

    override fun toGreekTimestamp(timestamp: Long): Long {
        return try {
            val instant = Instant.fromEpochMilliseconds(timestamp)
            val greekTimeZone = TimeZone.of(TimezoneId.GREECE)
            val greekDateTime = instant.toLocalDateTime(greekTimeZone)
            greekDateTime.toInstant(greekTimeZone).toEpochMilliseconds()
        } catch (t: Throwable) {
            timestamp
        }
    }

    override fun getTimeDiffInDays(earlyTimestamp: Long, lateTimestamp: Long): Long {
        val msDiff = lateTimestamp - earlyTimestamp
        return msDiff.milliseconds.inWholeDays
    }

    override fun isSameDay(millis1: Long, millis2: Long): Boolean {
        return try {
            val instant1 = Instant.fromEpochMilliseconds(millis1)
            val instant2 = Instant.fromEpochMilliseconds(millis2)
            val timeZone = TimeZone.currentSystemDefault()

            val date1 = instant1.toLocalDateTime(timeZone).date
            val date2 = instant2.toLocalDateTime(timeZone).date

            date1 == date2
        } catch (t: Throwable) {
            false
        }
    }

    @OptIn(FormatStringsInDatetimeFormats::class)
    override fun format(timestamp: Long, format: String): String {
        return try {
            val instant = Instant.fromEpochMilliseconds(timestamp)
            val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
            val formatter = LocalDateTime.Format {
                byUnicodePattern(format)
            }
            formatter.format(dateTime)
        } catch (t: Throwable) {
            ""
        }
    }

    override fun getCurrentLocalDateTime(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }
}