package mashup.littleforest.wooriforest.ui

import com.tistory.blackjinbase.util.Dlog
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFActivity
import mashup.littleforest.wooriforest.databinding.ActivityMainBinding

class MainActivity : WFActivity<ActivityMainBinding>(R.layout.activity_main) {

    override var logTag = "MainActivity"

    override fun onBackPressed() {
        super.onBackPressed()
        Dlog.d("onBackPressed")
    }
}