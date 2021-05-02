package mashup.littleforest.wooriforest.data

import android.util.Log
import mashup.littleforest.wooriforest.utils.PrefUtil
import okhttp3.Interceptor
import okhttp3.Response

//https://github.com/square/okhttp/wiki/Interceptors
class AddHeaderInterceptor() : Interceptor {

    private val tag = "okhttp"

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val token = PrefUtil.get(PrefUtil.PREF_ACCESS_TOKEN, "")
        Log.d(tag, "token : $token")

        //val headerToken = "Bearer $token"

        proceed(
            request()
                .newBuilder()
                .addHeader("Authorization", token)
                .build()
        )
    }
}