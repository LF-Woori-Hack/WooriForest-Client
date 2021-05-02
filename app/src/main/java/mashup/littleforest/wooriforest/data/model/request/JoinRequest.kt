package mashup.littleforest.wooriforest.data.model.request

data class JoinRequest(
    val query: String,
    val goalAmount: String,
    val comment: String
)