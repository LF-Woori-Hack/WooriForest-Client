package mashup.littleforest.wooriforest.data.model.request


import com.google.gson.annotations.SerializedName

data class LinkTransRequest(
    @SerializedName("carrier")
    val carrier: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("rrno")
    val rrno: String?
)