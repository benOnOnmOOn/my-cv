package benedykt.ziobro.cv.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Education(
    val degree: String,
    val fromDate: String,
    val name: String,
    val toDate: String
)