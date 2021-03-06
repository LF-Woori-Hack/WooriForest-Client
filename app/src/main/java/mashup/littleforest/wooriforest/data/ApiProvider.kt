package mashup.littleforest.wooriforest.data

import mashup.littleforest.wooriforest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiProvider {

    private const val baseUrl = "http://13.125.150.163:8080/"

    private const val naverBaseUrl = "https://openapi.naver.com/v1/"

    fun <T> provideApi(
        service: Class<T>
    ): T = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(getOkhttpClient())
        .addConverterFactory(getGsonConverter())
        .build()
        .create(service)

    fun provideWooriApi(): WooriForestApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(getOkhttpClient())
        .addConverterFactory(getGsonConverter())
        .build()
        .create(WooriForestApi::class.java)

    fun provideNaverApi(): NaverShopApi = Retrofit.Builder()
        .baseUrl(naverBaseUrl)
        .client(getNaverOkhttpClient())
        .addConverterFactory(getGsonConverter())
        .build()
        .create(NaverShopApi::class.java)

    private fun getGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    private fun getOkhttpClient(): OkHttpClient = OkHttpClient.Builder().apply {

        //TimeOut 시간을 지정합니다.
        readTimeout(60, TimeUnit.SECONDS)
        connectTimeout(60, TimeUnit.SECONDS)
        writeTimeout(5, TimeUnit.SECONDS)

        // 이 클라이언트를 통해 오고 가는 네트워크 요청/응답을 로그로 표시하도록 합니다.
        addInterceptor(getLoggingInterceptor())

        // header 에 정보를 추가해 준다.
        addInterceptor(AddHeaderInterceptor())

    }.build()

    private fun getNaverOkhttpClient(): OkHttpClient = OkHttpClient.Builder().apply {

        //TimeOut 시간을 지정합니다.
        readTimeout(60, TimeUnit.SECONDS)
        connectTimeout(60, TimeUnit.SECONDS)
        writeTimeout(5, TimeUnit.SECONDS)

        // 이 클라이언트를 통해 오고 가는 네트워크 요청/응답을 로그로 표시하도록 합니다.
        addInterceptor(getLoggingInterceptor())

        // header 에 정보를 추가해 준다.
        addInterceptor(AddNaverHeaderInterceptor())

    }.build()

    private fun getLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
}