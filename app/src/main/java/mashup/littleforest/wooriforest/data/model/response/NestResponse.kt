package mashup.littleforest.wooriforest.data.model.response


import com.google.gson.annotations.SerializedName

data class NestResponse(
    @SerializedName("query")
    val query: String?,
    @SerializedName("goalAmount")
    val goalAmount: Long?,
    @SerializedName("comment")
    val comment: String?,
    @SerializedName("currentAmount")
    val currentAmount: Long?,
    @SerializedName("monthlyPayment")
    val monthlyPayment: Long?,
    @SerializedName("cheeringCount")
    val cheeringCount: Int?
)
