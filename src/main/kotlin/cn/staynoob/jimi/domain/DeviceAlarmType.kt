package cn.staynoob.jimi.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class DeviceAlarmType(
        val value: Int,
        val description: String
) {
    ALARM_TYPE_1(1, "SOS求救"),
    ALARM_TYPE_2(2, "断电报警"),
    ALARM_TYPE_3(3, "震动报警"),
    ALARM_TYPE_4(4, "进入电子围栏(终端)"),
    ALARM_TYPE_5(5, "离开电子围栏(终端)"),
    ALARM_TYPE_6(6, "超速报警"),
    ALARM_TYPE_9(9, "位移报警"),
    ALARM_TYPE_10(10, "进卫星盲区报警"),
    ALARM_TYPE_11(11, "出卫星盲区报警"),
    ALARM_TYPE_12(12, "开机报警"),
    ALARM_TYPE_13(13, "卫星第一次定位报警"),
    ALARM_TYPE_14(14, "外电低电报警"),
    ALARM_TYPE_15(15, "外电低电保护报警"),
    ALARM_TYPE_16(16, "换卡报警"),
    ALARM_TYPE_17(17, "关机报警"),
    ALARM_TYPE_18(18, "外电低电保护后飞行模式报警"),
    ALARM_TYPE_19(19, "拆卸报警"),
    ALARM_TYPE_20(20, "门报警"),
    ALARM_TYPE_22(22, "声控报警"),
    ALARM_TYPE_23(23, "伪基站报警"),
    ALARM_TYPE_24(24, "开盖报警"),
    ALARM_TYPE_25(25, "内部电池低电报警"),
    ALARM_TYPE_32(32, "进入深度休眠报警"),
    ALARM_TYPE_50(50, "拔出报警"),
    ALARM_TYPE_90(90, "低电报警"),
    ALARM_TYPE_128(128, "后视镜震动报警"),
    ALARM_TYPE_192(192, "非法移动告警"),
    ALARM_TYPE_194(194, "后备电池电量不足告警"),
    ALARM_TYPE_195(195, "越界告警"),
    ALARM_TYPE_1001(1001, "ACC关闭"),
    ALARM_TYPE_1002(1002, "ACC开启"),
    ALARM_TYPE_1003(1003, "离线告警"),
    ALARM_TYPE_1004(1004, "停留告警"),
    ALARM_TYPE_1005(1005, "逗留告警"),
    ALARM_TYPE_1006(1006, "进入围栏"),
    ALARM_TYPE_1007(1007, "离开围栏"),
    ALARM_TYPE_1008(1008, "长时间不进围栏"),
    ALARM_TYPE_1009(1009, "长时间不出围栏"),
    ALARM_TYPE_1010(1010, "黑车围栏告警"),
    ALARM_TYPE_1011(1011, "超速报警(平台)"),
    ALARM_TYPE_1012(1012, "风险点告警");

    @JsonValue
    fun toValue(): Int {
        return this.value
    }

    companion object {
        private val map = DeviceAlarmType.values()
                .map { it.value to it }
                .toMap()

        @JvmStatic
        @JsonCreator
        fun fromValue(value: Int): DeviceAlarmType {
            return map[value]!!
        }
    }

}