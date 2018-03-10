package cn.staynoob.jimi.http

import cn.staynoob.jimi.JimiProperties
import cn.staynoob.jimi.JimiTokenHolder
import cn.staynoob.jimi.appendParam
import cn.staynoob.jimi.getSharedParamMap
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response

internal class JimiSharedParamInterceptor(
        private val properties: JimiProperties
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        var newFormBody = original.body()
        if (original.method() == "POST") {
            val originalBody = original.body()
            if (originalBody is FormBody?) {
                newFormBody =
                        appendParam(originalBody, getSharedParamMap(properties))
            }
        }
        val request = original.newBuilder()
                .method(original.method(), newFormBody)
                .build()
        return chain.proceed(request)
    }
}

internal class JimiTokenInterceptor(
        private val jimiTokenHolder: JimiTokenHolder
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        var newFormBody = original.body()
        if (original.method() == "POST") {
            val originalBody = original.body()
            if (originalBody is FormBody?) {
                newFormBody = appendParam(
                        originalBody,
                        mapOf(
                                "access_token" to jimiTokenHolder.getAccessToken()
                        )
                )
            }
        }
        val request = original.newBuilder()
                .method(original.method(), newFormBody)
                .build()
        return chain.proceed(request)
    }
}
