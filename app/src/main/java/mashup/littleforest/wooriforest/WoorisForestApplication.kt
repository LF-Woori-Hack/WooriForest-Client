package mashup.littleforest.wooriforest

import android.app.Application
import mashup.littleforest.wooriforest.utils.PrefUtil

class WoorisForestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PrefUtil.init(this)
    }
}