package com.nickstamp.kit.core.helpers

import kotlinx.datetime.LocalDateTime

interface DateTimeHelper {
    fun minutesToMilliseconds(minutes: Int): Long
    fun getCurrentTimeInMillis(): Long
    fun getLocalTimeInMillisFromEpgBackendFormat(dateTimeStr: String): Long
    fun toGreekTimestamp(timestamp: Long): Long
    fun getTimeDiffInDays(earlyTimestamp: Long, lateTimestamp: Long): Long
    fun isSameDay(millis1: Long, millis2: Long): Boolean
    fun format(timestamp: Long, format: String): String
    fun getCurrentLocalDateTime(): LocalDateTime
}