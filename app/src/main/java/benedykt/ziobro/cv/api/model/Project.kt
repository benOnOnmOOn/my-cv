package benedykt.ziobro.cv.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Project(
    val description: String,
    val name: String,
    val technologies: List<String>
)