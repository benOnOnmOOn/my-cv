package benedykt.ziobro.cv.viewmodel.model

data class Experience(
    val fromDate: String,
    val name: String,
    val projects: List<Project>,
    val toDate: String?
)