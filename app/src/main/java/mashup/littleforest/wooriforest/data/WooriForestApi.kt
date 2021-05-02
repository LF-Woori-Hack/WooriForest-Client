package mashup.littleforest.wooriforest.data

import mashup.littleforest.wooriforest.data.model.request.JoinRequest
import mashup.littleforest.wooriforest.data.model.request.LinkTransRequest
import mashup.littleforest.wooriforest.data.model.request.LoginRequest
import mashup.littleforest.wooriforest.data.model.response.LinkTransItem
import mashup.littleforest.wooriforest.data.model.response.LoginResponse
import mashup.littleforest.wooriforest.data.model.response.SuccessResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WooriForestApi {

    @POST("/v1/login")
    suspend fun login(
        request: LoginRequest
    ): LoginResponse

    @POST("/v1/linkTrans")
    suspend fun linkTrans(
        request: LinkTransRequest
    ): List<LinkTransItem>

    @GET("/v1/nest/detail")
    suspend fun nestDetail(
        @Query("id") id: String
    ): List<LinkTransItem>

    @GET("/v1/nest/join")
    suspend fun join(
        @Query("id") id: Int
    ): SuccessResponse

    @POST("/v1/saving/join")
    suspend fun join(
        request: JoinRequest
    ): List<SuccessResponse>
}