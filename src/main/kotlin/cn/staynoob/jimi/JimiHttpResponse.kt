package cn.staynoob.jimi

data class JimiHttpResponse<out T>(
        val code: Int,
        val message: String,
        val result: T?
)

