package benedykt.ziobro.cv.repository.model

data class Project(
    val description: String,
    val name: String,
    val technologies: List<String>
)