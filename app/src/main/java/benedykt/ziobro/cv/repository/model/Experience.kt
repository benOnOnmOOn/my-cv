package benedykt.ziobro.cv.repository.model

data class Experience(
    val fromDate: String,
    val name: String,
    val projects: List<Project>,
    val toDate: String?
)