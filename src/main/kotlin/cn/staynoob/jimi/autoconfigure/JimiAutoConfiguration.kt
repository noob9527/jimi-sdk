package cn.staynoob.jimi.autoconfigure

import cn.staynoob.jimi.*
import cn.staynoob.jimi.http.JimiApiService
import cn.staynoob.jimi.http.JimiSharedParamInterceptor
import cn.staynoob.jimi.http.JimiTokenInterceptor
import cn.staynoob.jimi.http.JimiTokenService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.boot.autoconfigure.AutoConfigureOrder
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Configuration
@AutoConfigureOrder
@EnableConfigurationProperties(JimiProperties::class)
class JimiAutoConfiguration(
        private val properties: JimiProperties
) {

    private val loggingInterceptor = HttpLoggingInterceptor()
            .apply {
                level = properties.logLevel
            }

    companion object {
        private val jacksonConverterFactory =
                JacksonConverterFactory.create(objectMapper)
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun jimiTokenService(): JimiTokenService {
        val client = OkHttpClient.Builder()
                .addInterceptor(JimiSharedParamInterceptor(properties))
                .addInterceptor(loggingInterceptor)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(properties.apiUrl)
                .addConverterFactory(jacksonConverterFactory)
                .client(client)
                .build()
        return retrofit.create(JimiTokenService::class.java)
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun jimiTokenHolder(
            tokenService: JimiTokenService
    ): JimiTokenHolder {
        return JimiTokenHolder(properties, tokenService)
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun jimiApiService(
            jimiTokenHolder: JimiTokenHolder
    ): JimiApiService {
        val client = OkHttpClient.Builder()
                .addInterceptor(JimiSharedParamInterceptor(properties))
                .addInterceptor(JimiTokenInterceptor(jimiTokenHolder))
                .addInterceptor(loggingInterceptor)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(properties.apiUrl)
                .addConverterFactory(jacksonConverterFactory)
                .client(client)
                .build()
        return retrofit.create(JimiApiService::class.java)
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun jimiService(
            jimiApiService: JimiApiService,
            jimiProperties: JimiProperties
    ): JimiService {
        return JimiServiceImpl(jimiApiService, jimiProperties)
    }
}