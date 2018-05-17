package cn.staynoob.jimi

import cn.staynoob.jimi.util.NoArgConstructor
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.boot.context.properties.ConfigurationProperties
import javax.annotation.PostConstruct

/**
 * this class may be construct by two different ways
 * let's say the kotlin way(by primary constructor)
 * and the java way(by Class.instantiate())
 * this class has to be mutable, otherwise spring won't be
 * able to set up its property since spring create this class via the "java" way.
 */
@NoArgConstructor
@ConfigurationProperties(prefix = "jimi")
class JimiProperties(
        var appKey: String,
        var appSecret: String,
        var userId: String,
        userPassword: String,
        var apiVersion: String = "0.9",
        var apiUrl: String = "http://open.aichezaixian.com/route/rest/",
        var logLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.NONE
) {
    var userPassword: String = userPassword
        set(value) {
            field = value
            userPwdMd5Hex = md5Hex(value)
        }

    // insure userPwdMd5Hex is not null
    init {
        this.userPassword = userPassword
    }

    lateinit var userPwdMd5Hex: String
        private set

    /**
     * fix kotlin init logic dose not execute issue
     */
    @Suppress("SENSELESS_COMPARISON")
    @PostConstruct
    private fun init() {
        if (apiVersion == null) apiVersion = "0.9"
        if (apiUrl == null) apiUrl = "http://open.aichezaixian.com/route/rest/"
        if (logLevel == null) logLevel = HttpLoggingInterceptor.Level.NONE
    }

    override fun toString(): String {
        return "JimiProperties(appKey='$appKey'," +
                " appSecret='$appSecret'," +
                " userId='$userId'," +
                " apiVersion='$apiVersion'," +
                " apiUrl='$apiUrl'," +
                " userPassword='$userPassword'," +
                " userPwdMd5Hex='$userPwdMd5Hex')"
    }
}

