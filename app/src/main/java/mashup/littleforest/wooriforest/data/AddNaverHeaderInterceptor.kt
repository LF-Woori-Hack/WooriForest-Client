package mashup.littleforest.wooriforest.data

import okhttp3.Interceptor

class AddNaverHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", "imEQgioEduKsCAJhvl5m")
                .addHeader("X-Naver-Client-Secret", "GEd34Z6U0t")
                .build()
        )
    }
}