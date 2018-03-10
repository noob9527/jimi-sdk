package cn.staynoob.jimi

enum class JimiSignMethod(
        val methodName: String,
        val method: (String) -> String
) {
    MD5_HEX("md5", ::md5Hex)
}