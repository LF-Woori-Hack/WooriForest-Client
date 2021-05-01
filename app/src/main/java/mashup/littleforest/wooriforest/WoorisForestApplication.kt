package mashup.littleforest.wooriforest

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.tistory.blackjinbase.util.Dlog
import mashup.littleforest.wooriforest.utils.PrefUtil

class WoorisForestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDlog()
        initPref()
        initKakaoLogin()
    }

    private fun initDlog() {
        Dlog.initDebug(BuildConfig.DEBUG)
    }

    private fun initPref() {
        PrefUtil.init(this)
    }

    private fun initKakaoLogin() {
        KakaoSdk.init(this, "2c654b1de0826cb908fb1388a84c0a9b")
        // SDK 초기화
        /*KakaoSDK.init(object : KakaoAdapter() {
            override fun getApplicationConfig(): IApplicationConfig {
                return IApplicationConfig { this@WoorisForestApplication }
            }
        })*/
    }
}