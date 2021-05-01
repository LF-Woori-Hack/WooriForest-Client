package mashup.littleforest.wooriforest

import android.annotation.SuppressLint
import android.app.Application
import android.provider.Settings
import com.kakao.sdk.common.KakaoSdk
import com.tistory.blackjinbase.util.Dlog
import mashup.littleforest.wooriforest.utils.PrefUtil

class WoorisForestApplication : Application() {

    companion object {

        lateinit var instance: WoorisForestApplication

        lateinit var deviceUuid: String

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        deviceUuid = getDeviceUUid()

        initDlog()
        initPref()
        initKakaoLogin()
    }

    @SuppressLint("HardwareIds")
    private fun getDeviceUUid() = Settings.Secure.getString(instance.contentResolver, Settings.Secure.ANDROID_ID)

    private fun initDlog() {
        Dlog.initDebug(BuildConfig.DEBUG)
    }

    private fun initPref() {
        PrefUtil.init(this)
    }

    private fun initKakaoLogin() {
        KakaoSdk.init(this, "2c654b1de0826cb908fb1388a84c0a9b")
    }
}