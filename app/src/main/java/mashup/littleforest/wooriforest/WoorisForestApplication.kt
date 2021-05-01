package mashup.littleforest.wooriforest

import android.app.Application
import com.tistory.blackjinbase.util.Dlog
import mashup.littleforest.wooriforest.utils.PrefUtil

class WoorisForestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Dlog.initDebug(BuildConfig.DEBUG)
        PrefUtil.init(this)
    }
}