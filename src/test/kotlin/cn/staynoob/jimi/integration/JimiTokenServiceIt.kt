package cn.staynoob.jimi.integration

import cn.staynoob.jimi.JimiProperties
import cn.staynoob.jimi.autoconfigure.JimiAutoConfiguration
import cn.staynoob.jimi.http.JimiTokenService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration

@ImportAutoConfiguration(JimiAutoConfiguration::class)
@Disabled
class JimiTokenServiceIt : IntegrationTestBase() {

    @Autowired
    private lateinit var service: JimiTokenService
    @Autowired
    private lateinit var properties: JimiProperties

    @Test
    @DisplayName("test100")
    fun test100() {
        val call = service.getAccessTokem(
                userId = properties.userId,
                userPwdMd5 = properties.userPwdMd5Hex,
                expiresIn = 60
        )
        val res = call.execute()
        assertThat(res.body()).isNotNull()
    }
}