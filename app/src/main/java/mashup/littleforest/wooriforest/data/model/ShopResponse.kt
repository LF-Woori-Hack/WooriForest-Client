package mashup.littleforest.wooriforest.data.model


import com.google.gson.annotations.SerializedName

data class ShopResponse(
    @SerializedName("lastBuildDate")
    val lastBuildDate: String?,
    @SerializedName("total")
    val total: Double?,
    @SerializedName("start")
    val start: Double?,
    @SerializedName("display")
    val display: Double?,
    @SerializedName("items")
    val items: List<Item>?
) {
    data class Item(
        @SerializedName("title")
        val title: String?,
        @SerializedName("link")
        val link: String?,
        @SerializedName("image")
        val image: String?,
        @SerializedName("lprice")
        val lprice: Int?,
        @SerializedName("hprice")
        val hprice: String?,
        @SerializedName("mallName")
        val mallName: String?,
        @SerializedName("productId")
        val productId: Long?,
        @SerializedName("productType")
        val productType: Int?,
        @SerializedName("brand")
        val brand: String?,
        @SerializedName("maker")
        val maker: String?,
        @SerializedName("category1")
        val category1: String?,
        @SerializedName("category2")
        val category2: String?,
        @SerializedName("category3")
        val category3: String?,
        @SerializedName("category4")
        val category4: String?
    )
}