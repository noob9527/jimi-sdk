package cn.staynoob.jimi.domain.response

import cn.staynoob.jimi.domain.FenceAlarmType
import cn.staynoob.jimi.domain.MapType
import com.google.common.base.CaseFormat
import kotlin.reflect.full.memberProperties

data class Fence(
        val imei: String,
        val fenceName: String,
        val alarmType: FenceAlarmType,
        val reportMode: Int,
        val alarmSwitch: String,
        val lng: Double,
        val lat: Double,
        val radius: Int,    // 1～9999(100m)
        val zoomLevel: Int,
        val mapType: MapType,
        val instructNo: String? = null
) {
    fun toFieldMap(): Map<String, String> {
        return Fence::class.memberProperties
                .map {
                    CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, it.name) to it.get(this).toString()
                }
                .toMap()
    }
}

