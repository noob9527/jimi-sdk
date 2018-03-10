package cn.staynoob.jimi.domain

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class DeviceAlarm(
        val imei: String,
        val deviceName: String?,
        val alarmType: DeviceAlarmType,
        val alarmName: String,
        @get:JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        val alarmTime: LocalDateTime,
        val lat: Double,
        val lng: Double,
        val address: String? = null
)
