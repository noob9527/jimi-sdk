package cn.staynoob.jimi.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class FenceAlarmType(val value: String) {
    IN("in"),
    OUT("out"),
    IN_OUT("in,out");

    @JsonValue
    fun toValue(): String {
        return this.value
    }

    override fun toString() = toValue()

    companion object {
        private val map = FenceAlarmType.values()
                .map { it.value to it }
                .toMap()

        @JvmStatic
        @JsonCreator
        fun fromValue(value: String): FenceAlarmType {
            return map[value]!!
        }
    }
}