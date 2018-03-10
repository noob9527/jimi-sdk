package cn.staynoob.jimi

import cn.staynoob.jimi.domain.MapType
import cn.staynoob.jimi.domain.response.Device
import cn.staynoob.jimi.domain.response.DeviceLocation
import cn.staynoob.jimi.domain.response.Fence
import cn.staynoob.jimi.domain.response.TrackPoint
import cn.staynoob.jimi.http.JimiApiService
import java.time.Instant

internal class JimiServiceImpl(
        private val jimiApiService: JimiApiService,
        private val jimiProperties: JimiProperties
) : JimiService {
    override fun listUserDevice(): List<Device> {
        return listUserDevice(jimiProperties.userId)
    }

    override fun listUserDevice(target: String): List<Device> {
        return jimiApiService.listUserDevice(target).toResult()
    }

    override fun listUserDeviceLocation(mapType: MapType): List<DeviceLocation> {
        return listUserDeviceLocation(jimiProperties.userId, mapType)
    }

    override fun listUserDeviceLocation(target: String, mapType: MapType): List<DeviceLocation> {
        return jimiApiService.listUserDeviceLocation(
                target, mapType
        ).toResult()
    }

    override fun listDeviceTrack(
            imei: String,
            beginTime: Instant,
            endTime: Instant,
            mapType: MapType
    ): List<TrackPoint> {
        return jimiApiService.listDeviceTrack(
                imei,
                dateFormatter.format(beginTime),
                dateFormatter.format(endTime),
                mapType
        ).toResult()
    }

    override fun getDeviceLocation(
            imeis: Collection<String>,
            mapType: MapType
    ): List<DeviceLocation> {
        if (imeis.isEmpty())
            throw IllegalArgumentException("imeis parameter should not be empty")
        return jimiApiService.getDeviceLocation(
                imeis.joinToString(separator = ","),
                mapType
        ).toResult()
    }

    override fun getDeviceFence(imei: String): List<Fence> {
        return jimiApiService.getDeviceFence(imei).toResult()
    }

    override fun createDeviceFence(fence: Fence): String {
        return jimiApiService.createDeviceFence(
                fence.toFieldMap()
        ).toResult()
    }

    override fun deleteDeviceFence(imei: String, instructNo: String): String {
        return jimiApiService.deleteDeviceFence(
                imei, instructNo
        ).toResult()
    }

}