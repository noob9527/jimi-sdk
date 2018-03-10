package cn.staynoob.jimi.http

import cn.staynoob.jimi.JimiApiMethod
import cn.staynoob.jimi.JimiHttpResponse
import cn.staynoob.jimi.domain.response.JimiToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface JimiTokenService {
    @POST(".")
    @FormUrlEncoded
    fun getAccessTokem(
            @Field("user_id") userId: String,
            @Field("user_pwd_md5") userPwdMd5: String,
            @Field("expires_in") expiresIn: Int = 7200,
            @Field("method") method: JimiApiMethod = JimiApiMethod.OAUTH_TOKEN_GET
    ): Call<JimiHttpResponse<JimiToken>>

    @POST(".")
    @FormUrlEncoded
    fun refreshAccessToken(
            @Field("access_token") accessToken: String,
            @Field("refresh_token") refreshToken: String,
            @Field("expires_in") expiresIn: Int = 7200,
            @Field("method") method: JimiApiMethod = JimiApiMethod.OAUTH_TOKEN_REFRESH
    ): Call<JimiHttpResponse<JimiToken>>
}