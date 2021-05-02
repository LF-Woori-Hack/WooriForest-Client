package mashup.littleforest.wooriforest.data.model.request

data class LoginRequest(
    val deviceId: String,
    val snsToken: String,
    val snsType: String = "kakaoTalk"
)