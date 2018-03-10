package cn.staynoob.jimi.domain

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AlarmTypeTest {
    @Test
    @DisplayName("toJson")
    fun test100() {
        val json = ObjectMapper()
                .writeValueAsString(DeviceAlarmType.ALARM_TYPE_1)
        assertThat(json).isEqualTo("1")
    }

    @Test
    @DisplayName("fromJson")
    fun test200() {
        val mapper = ObjectMapper()
        val res = (1..3).map { mapper.readValue<DeviceAlarmType>(it.toString()) }
        assertThat(res).containsExactly(
                DeviceAlarmType.ALARM_TYPE_1,
                DeviceAlarmType.ALARM_TYPE_2,
                DeviceAlarmType.ALARM_TYPE_3
        )
    }
}