package cn.staynoob.jimi.domain.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime

/**
 * indicates if token is about to expire
 */
const val BUFFER_DURATION: Int = 20

data class JimiToken(
        val account: String,
        val accessToken: String,
        val refreshToken: String,
        val appKey: String,
        val expiresIn: Int,
        @get:JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        val time: LocalDateTime
) {
    @get:JsonIgnore
    val isExpired: Boolean
        get() = LocalDateTime.now() > time.plusSeconds(expiresIn.toLong())

    @get:JsonIgnore
    val isAboutToExpire: Boolean
        get() = LocalDateTime.now() > time.plusSeconds((expiresIn * 5 / 6).toLong())
}