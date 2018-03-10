package cn.staynoob.jimi.domain.response

import cn.staynoob.jimi.domain.response.JimiToken
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class JimiTokenTest {
    private lateinit var mapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        mapper = ObjectMapper()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .registerModule(JavaTimeModule())
                .registerKotlinModule()
    }

    @Test
    @DisplayName("serialize test")
    fun test100() {
        val token = JimiToken(
                "account",
                "accessToken",
                "refreshToken",
                "appKey",
                60,
                LocalDateTime.now()
        )
        mapper.writeValueAsString(token)
    }

    @Test
    @DisplayName("deserialize test")
    fun test200() {
        val json = """
            {
                "appKey": "8FB345B8693CCD003CC2DAB123456789",
                "account": "jimitest",
                "accessToken": "7da3330ec28e3996b6ef4a7123456789",
                "expiresIn": 60,
                "refreshToken": "7da3330ec28e3996b6ef4a7123456789",
                "time": "2017-06-15 10:00:00"
            }
        """
        mapper.readValue<JimiToken>(json)
    }

    @Test
    @DisplayName("isExpired")
    fun test300() {
        val token1 = JimiToken(
                "account",
                "accessToken",
                "refreshToken",
                "appKey",
                10,
                LocalDateTime.now().minusSeconds(11)
        )
        assertThat(token1.isExpired).isTrue()
        val token2 = token1.copy(
                time = LocalDateTime.now().minusSeconds(9)
        )
        assertThat(token2.isExpired).isFalse()
    }

    @Test
    @DisplayName("isAboutToExpire")
    fun test400() {
        val token1 = JimiToken(
                "account",
                "accessToken",
                "refreshToken",
                "appKey",
                60,
                LocalDateTime.now().minusSeconds(51)
        )
        assertThat(token1.isAboutToExpire).isTrue()
        val token2 = token1.copy(
                time = LocalDateTime.now().minusSeconds(49)
        )
        assertThat(token2.isAboutToExpire).isFalse()
    }
}