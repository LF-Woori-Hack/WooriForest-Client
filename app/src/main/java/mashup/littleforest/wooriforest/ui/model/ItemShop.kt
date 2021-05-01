package mashup.littleforest.wooriforest.ui.model

data class ItemShop(
    val image: String,
    val title: String,
    val price: Int,
    val link: String,
    val listener: (item: ItemShop) -> Unit
)