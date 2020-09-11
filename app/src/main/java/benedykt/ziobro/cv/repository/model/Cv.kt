package benedykt.ziobro.cv.repository.model

data class Cv(
    val age: Int,
    val birthDate: String,
    val education: List<Education>,
    val experience: List<Experience>,
    val firstName: String,
    val languages: List<Language>,
    val phoneNumber: String,
    val secondName: String,
    val trainings: List<String>
)