package cn.staynoob.jimi.http

import cn.staynoob.jimi.JimiApiMethod
import cn.staynoob.jimi.JimiHttpResponse
import cn.staynoob.jimi.domain.MapType
import cn.staynoob.jimi.domain.response.Device
import cn.staynoob.jimi.domain.response.DeviceLocation
import cn.staynoob.jimi.domain.response.Fence
import cn.staynoob.jimi.domain.response.TrackPoint
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

internal interface JimiApiService {

    @POST(".")
    @FormUrlEncoded
    fun listUserDevice(
            @Field("target") target: String,
            @Field("method") method: JimiApiMethod = JimiApiMethod.USER_DEVICE_LIST
    ): Call<JimiHttpResponse<List<Device>>>

    @POST(".")
    @FormUrlEncoded
    fun listUserDeviceLocation(
            @Field("target") target: String,
            @Field("map_type") mapType: MapType,
            @Field("method") method: JimiApiMethod = JimiApiMethod.USER_DEVICE_LOCATION_LIST
    ): Call<JimiHttpResponse<List<DeviceLocation>>>

    @POST(".")
    @FormUrlEncoded
    fun listDeviceTrack(
            @Field("imei") imei: String,
            @Field("begin_time") beginTime: String,
            @Field("end_time") endTime: String,
            @Field("map_type") mapType: MapType,
            @Field("method") method: JimiApiMethod = JimiApiMethod.DEVICE_TRACK_LIST
    ): Call<JimiHttpResponse<List<TrackPoint>>>

    @POST(".")
    @FormUrlEncoded
    fun getDeviceLocation(
            @Field("imeis") imeis: String,
            @Field("map_type") mapType: MapType,
            @Field("method") method: JimiApiMethod = JimiApiMethod.DEVICE_LOCATION_GET
    ): Call<JimiHttpResponse<List<DeviceLocation>>>

    @POST(".")
    @FormUrlEncoded
    fun getDeviceFence(
            @Field("imei") imei: String,
            @Field("method") method: JimiApiMethod = JimiApiMethod.OPEN_DEVICE_FENCE_GET
    ): Call<JimiHttpResponse<List<Fence>>>

    @POST(".")
    @FormUrlEncoded
    fun createDeviceFence(
            @FieldMap fields: Map<String, String>,
            @Field("method") method: JimiApiMethod = JimiApiMethod.OPEN_DEVICE_FENCE_CREATE
    ): Call<JimiHttpResponse<String>>

    @POST(".")
    @FormUrlEncoded
    fun deleteDeviceFence(
            @Field("imei") imei: String,
            @Field("instruct_no") instructNo: String,
            @Field("method") method: JimiApiMethod = JimiApiMethod.OPEN_DEVICE_FENCE_DELETE
    ): Call<JimiHttpResponse<String>>

}

