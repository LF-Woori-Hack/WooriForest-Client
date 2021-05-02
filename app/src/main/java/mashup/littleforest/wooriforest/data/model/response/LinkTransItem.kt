package mashup.littleforest.wooriforest.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinkTransItem(
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("iconImage")
    val iconImage: String? = "",
    @SerializedName("memberCount")
    val memberCount: Int = 0,

    //local
    val isShowMemberCount: Boolean = false,
    val isShowSelectedBox: Boolean = true,
    val isSelected: Boolean = false
) : Parcelable