package mashup.littleforest.wooriforest.ui.customfont

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

class SunflowerBoldTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    init {
        typeface = Typeface.createFromAsset(context.assets, "fonts/Sunflower-Bold.ttf")
    }

}