package cn.staynoob.jimi

import cn.staynoob.jimi.domain.response.JimiToken
import cn.staynoob.jimi.http.JimiTokenService

class JimiTokenHolder(
        private val property: JimiProperties,
        private val tokenService: JimiTokenService
) {
    private var token: JimiToken? = null

    /**
     * get a valid token from cache or jimi service
     *
     * if token is null or token is expired, fetch and return cached token
     * else if token is about to expire, refresh and return cached token
     * else return cached token
     */
    @Synchronized
    fun getAccessToken(): String {
        val token = this.token
        if (token == null || token.isExpired) {
            val newToken = tokenService
                    .getAccessTokem(
                            property.userId,
                            property.userPwdMd5Hex
                    ).toResult()
            this.token = newToken
            return newToken.accessToken
        } else if (token.isAboutToExpire) {
            val newToken = tokenService
                    .refreshAccessToken(
                            token.accessToken,
                            token.refreshToken
                    ).toResult()
            this.token = newToken
            return newToken.accessToken
        } else {
            return token.accessToken
        }
    }

}