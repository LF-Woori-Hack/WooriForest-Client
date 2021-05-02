package mashup.littleforest.wooriforest.data

import mashup.littleforest.wooriforest.data.model.response.ShopResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverShopApi {

    @GET("search/shop.json")
    suspend fun shop(
        @Query("query") query: String,
        @Query("display") display: Int = 100,
        @Query("start") start: Int = 1,
        @Query("sort") sort: String = "sim"
    ): ShopResponse
}