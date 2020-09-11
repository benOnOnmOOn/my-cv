package benedykt.ziobro.cv.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Language(
    val level: String,
    val name: String
)