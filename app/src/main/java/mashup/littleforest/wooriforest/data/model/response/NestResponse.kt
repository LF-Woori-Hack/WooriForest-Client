package mashup.littleforest.wooriforest.data.model.response


import com.google.gson.annotations.SerializedName

data class NestResponse(
    @SerializedName("query")
    val query: String?,
    @SerializedName("goalAmount")
    val goalAmount: String?,
    @SerializedName("comment")
    val comment: String?,
    @SerializedName("currentAmount")
    val currentAmount: String?,
    @SerializedName("monthlyPayment")
    val monthlyPayment: String?,
    @SerializedName("cheeringCount")
    val cheeringCount: String?,

    //local
    val image: String = ""
)