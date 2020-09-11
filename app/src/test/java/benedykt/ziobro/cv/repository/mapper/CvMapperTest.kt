package benedykt.ziobro.cv.repository.mapper

import benedykt.ziobro.cv.repository.model.Cv
import benedykt.ziobro.cv.repository.model.Education
import benedykt.ziobro.cv.repository.model.Experience
import benedykt.ziobro.cv.repository.model.Language
import benedykt.ziobro.cv.repository.model.Project
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.KoinTest
import benedykt.ziobro.cv.api.model.Education as ResponseEducation
import benedykt.ziobro.cv.api.model.Language as ResponseLanguage
import benedykt.ziobro.cv.api.model.Project as ResponseProject
import benedykt.ziobro.cv.api.model.Experience as ResponseExperience
import benedykt.ziobro.cv.api.model.Cv as ResponseCv

@RunWith(JUnit4::class)
class CvMapperTest : KoinTest {

    @Test
    fun `toRepositoryProject - check if it correct map ResponseProject to RepositoryProject`() {
        val result = RESPONSE_PROJECT.toRepositoryProject()
        Assert.assertEquals(EXPECTED_PROJECT, result)
    }

    @Test
    fun `toRepositoryExperience - check if it correct map ResponseExperience to RepositoryExperience`() {
        val result = RESPONSE_EXPERIENCE.toRepositoryExperience()
        Assert.assertEquals(EXPECTED_EXPERIENCE, result)
    }

    @Test
    fun `toRepositoryEducation - check if it correct map ResponseExperience to RepositoryExperience`() {
        val result = RESPONSE_EDUCATION.toRepositoryEducation()
        Assert.assertEquals(EXPECTED_EDUCATION, result)
    }

    @Test
    fun `toRepositoryLanguage- check if it correct map ResponseExperience to RepositoryExperience`() {
        val result = RESPONSE_LANGUAGE.toRepositoryLanguage()
        Assert.assertEquals(EXPECTED_LANGUAGE, result)
    }

    @Test
    fun `toRepositoryCv - check if it correct map ResponseExperience to RepositoryExperience`() {
        val result = RESPONSE_CV.toRepositoryCv()
        Assert.assertEquals(EXPECTED_REPOSITORY_CV, result)
    }

    companion object {
        private val RESPONSE_TRAININGS = listOf("Short training", "Long training")

        private val RESPONSE_PROJECT = ResponseProject(
            description = "Simple project",
            name = "Roundo",
            technologies = listOf("Java")
        )

        private val RESPONSE_EXPERIENCE = ResponseExperience(
            fromDate = "01-01-1990",
            name = "Company",
            toDate = "now",
            projects = listOf(RESPONSE_PROJECT)
        )

        private val RESPONSE_EDUCATION = ResponseEducation(
            degree = "a",
            fromDate = "from",
            name = "school",
            toDate = "to"
        )

        private val RESPONSE_LANGUAGE = ResponseLanguage(
            level = "c2",
            name = "Polish"
        )

        val RESPONSE_CV = ResponseCv(
            age = 1,
            birthDate = "29-10-2345",
            education = listOf(RESPONSE_EDUCATION),
            firstName = "John",
            secondName = "Knox",
            phoneNumber = "334 4566 433",
            languages = listOf(RESPONSE_LANGUAGE),
            experience = listOf(RESPONSE_EXPERIENCE),
            trainings = RESPONSE_TRAININGS
        )

        private val EXPECTED_TRAININGS = listOf("Short training", "Long training")

        private val EXPECTED_PROJECT = Project(
            description = "Simple project",
            name = "Roundo",
            technologies = listOf("Java")
        )

        private val EXPECTED_EXPERIENCE = Experience(
            fromDate = "01-01-1990",
            name = "Company",
            toDate = "now",
            projects = listOf(EXPECTED_PROJECT)
        )

        private val EXPECTED_EDUCATION = Education(
            degree = "a",
            fromDate = "from",
            name = "school",
            toDate = "to"
        )

        private val EXPECTED_LANGUAGE = Language(level = "c2", name = "Polish")

        val EXPECTED_REPOSITORY_CV = Cv(
            age = 1,
            birthDate = "29-10-2345",
            education = listOf(EXPECTED_EDUCATION),
            firstName = "John",
            secondName = "Knox",
            phoneNumber = "334 4566 433",
            languages = listOf(EXPECTED_LANGUAGE),
            experience = listOf(EXPECTED_EXPERIENCE),
            trainings = EXPECTED_TRAININGS
        )

    }
}