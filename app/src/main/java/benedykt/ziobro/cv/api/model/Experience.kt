package benedykt.ziobro.cv.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Experience(
    val fromDate: String,
    val name: String,
    val projects: List<Project>,
    val toDate: String
)