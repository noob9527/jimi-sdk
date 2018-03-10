package cn.staynoob.jimi.integration

import cn.staynoob.jimi.JimiProperties
import cn.staynoob.jimi.JimiService
import cn.staynoob.jimi.autoconfigure.JimiAutoConfiguration
import cn.staynoob.jimi.domain.FenceAlarmType
import cn.staynoob.jimi.domain.MapType
import cn.staynoob.jimi.domain.response.Fence
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import java.time.Instant
import java.time.temporal.ChronoUnit

@ImportAutoConfiguration(JimiAutoConfiguration::class)
class JimiServiceIt : IntegrationTestBase() {

    @Autowired
    private lateinit var service: JimiService
    @Autowired
    private lateinit var properties: JimiProperties

    @Value("\${jimi-test-imei}")
    private lateinit var testImei: String

    @Test
    @DisplayName("listUserDeviceTest100")
    fun listUserDeviceTest100() {
        val res = service.listUserDevice(properties.userId)
        assertThat(res).isNotNull
    }

    @Test
    @DisplayName("listUserDeviceLocationTest100")
    fun listUserDeviceLocationTest100() {
        val res = service.listUserDeviceLocation(
                properties.userId, MapType.GOOGLE
        )
        assertThat(res).isNotNull
    }

    @Test
    @DisplayName("listDeviceTrackTest100")
    fun listDeviceTrackTest100() {
        val end = Instant.now()
        val begin = end.minus(1, ChronoUnit.DAYS)
        val res = service.listDeviceTrack(
                testImei,
                begin,
                end,
                MapType.GOOGLE
        )
        assertThat(res).isNotNull
    }

    @Test
    @DisplayName("getDeviceLocationTest")
    fun getDeviceLocationTest() {
        val res = service.getDeviceLocation(listOf(testImei), MapType.GOOGLE)
        assertThat(res).isNotNull()
    }

    @Test
    @DisplayName("getDeviceFenceTest")
    fun getDeviceFenceTest() {
        val res = service.getDeviceFence(testImei)
        assertThat(res).isNotNull
    }

    @Test
    @DisplayName("manipulateDeviceFenceTest")
    fun manipulateDeviceFenceTest() {
        val fence = Fence(
                testImei,
                "testFence",
                FenceAlarmType.IN_OUT,
                0,
                "OFF",
                0.0,
                0.0,
                1,
                3,
                MapType.GOOGLE
        )
        val res1 = service.createDeviceFence(fence)
        val res2 = service.deleteDeviceFence(testImei, res1)
        assertThat(res1).isNotEmpty()
        assertThat(res2).isNotEmpty()
        println(res1)
        println(res2)
    }
}