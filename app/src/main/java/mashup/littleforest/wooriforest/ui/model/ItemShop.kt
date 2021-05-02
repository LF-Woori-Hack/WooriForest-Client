package mashup.littleforest.wooriforest.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemShop(
    val image: String,
    val title: String,
    val price: Int,
    val link: String,
    val listener: (item: ItemShop) -> Unit
) : Parcelable