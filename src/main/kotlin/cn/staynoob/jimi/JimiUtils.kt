package cn.staynoob.jimi

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.FormBody
import org.apache.commons.codec.digest.DigestUtils
import retrofit2.Call
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

internal val objectMapper = ObjectMapper()
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .registerModule(JavaTimeModule())
        .registerKotlinModule()

internal val dateFormatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss")
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.systemDefault())

internal fun md5Hex(input: String) = DigestUtils.md5Hex(input)!!

internal operator fun FormBody.iterator(): Iterator<Pair<String, String>> {
    return object : Iterator<Pair<String, String>> {
        private var currentIndex = 0
        override fun hasNext(): Boolean {
            return currentIndex < this@iterator.size()
        }

        override fun next(): Pair<String, String> {
            val res = this@iterator.name(currentIndex) to this@iterator.value(currentIndex)
            currentIndex += 1
            return res
        }
    }
}

internal fun <T> Call<out JimiHttpResponse<T>>.exec(): JimiHttpResponse<T> {
    val res = this.execute()
    if (!res.isSuccessful) {
        val errMsg = res.errorBody()?.string()
        var message = res.message()
        if (errMsg != null) {
            message = try {
                objectMapper
                        .readValue<JimiHttpResponse<Any>>(errMsg)
                        .message
            } catch (e: Exception) {
                errMsg
            }
        }
        throw JimiApiException(message)
    }
    val body = res.body()!!
    if (body.code != 0) {
        throw JimiApiException(body.message)
    }
    return body
}

internal fun <T> Call<out JimiHttpResponse<T>>.toResult(): T {
    return this.exec().result!!
}

internal fun appendParam(
        formBody: FormBody?,
        param: Map<String, String>
): FormBody {
    val original = formBody?.iterator()?.asSequence()?.toMap()
            ?: mapOf()
    val builder = FormBody.Builder()
    (param + original).entries
            .forEach { builder.add(it.key, it.value) }
    return builder.build()
}

internal const val HARDCODE_SIGN_METHOD = "md5"
internal const val HARDCODE_FORMAT = "json"

internal fun getSharedParamMap(properties: JimiProperties): Map<String, String> {
    return mapOf(
            "app_key" to properties.appKey,
            "timestamp" to LocalDateTime.now().format(dateFormatter),
            "v" to properties.apiVersion,
            "sign_method" to HARDCODE_SIGN_METHOD,
            "format" to HARDCODE_FORMAT
    )
}