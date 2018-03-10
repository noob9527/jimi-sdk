package cn.staynoob.jimi

import cn.staynoob.jimi.domain.MapType
import cn.staynoob.jimi.domain.response.Device
import cn.staynoob.jimi.domain.response.DeviceLocation
import cn.staynoob.jimi.domain.response.Fence
import cn.staynoob.jimi.domain.response.TrackPoint
import java.time.Instant

interface JimiService {

    fun listUserDevice(): List<Device>

    fun listUserDevice(target: String): List<Device>

    fun listUserDeviceLocation(mapType: MapType = MapType.GOOGLE): List<DeviceLocation>

    fun listUserDeviceLocation(target: String, mapType: MapType): List<DeviceLocation>

    fun listDeviceTrack(
            imei: String,
            beginTime: Instant,
            endTime: Instant,
            mapType: MapType
    ): List<TrackPoint>

    fun getDeviceLocation(
            imeis: Collection<String>,
            mapType: MapType
    ): List<DeviceLocation>

    fun getDeviceFence(imei: String): List<Fence>

    fun createDeviceFence(fence: Fence): String

    fun deleteDeviceFence(
            imei: String,
            instructNo: String
    ): String
}