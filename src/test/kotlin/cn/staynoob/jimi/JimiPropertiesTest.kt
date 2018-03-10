package cn.staynoob.jimi

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class JimiPropertiesTest {
    @Test
    @DisplayName("construct via primary constructor should set up passwordMd5")
    fun test100() {
        val prop = JimiProperties(
                "",
                "",
                "",
                "123"
        )
        assertThat(prop.userPwdMd5Hex).isNotEmpty()
    }

    @Test
    @DisplayName("userPassword setter should set up passwordMd5")
    fun test200() {
        val prop = JimiProperties::class.java.newInstance()
                .apply {
                    userPassword = "123"
                }
        assertThat(prop.userPwdMd5Hex).isNotEmpty()
    }
}