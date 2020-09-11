package benedykt.ziobro.cv.repository

import benedykt.ziobro.cv.api.model.*
import benedykt.ziobro.cv.api.service.CvService
import benedykt.ziobro.cv.di.appModules
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.times
import retrofit2.Response
import benedykt.ziobro.cv.repository.model.Cv as RepositoryCv
import benedykt.ziobro.cv.repository.model.Education as RepositoryEducation
import benedykt.ziobro.cv.repository.model.Experience as RepositoryExperience
import benedykt.ziobro.cv.repository.model.Language as RepositoryLanguage
import benedykt.ziobro.cv.repository.model.Project as RepositoryProject

class CvRepositoryImplTest : KoinTest {

    private val cvRepository: CvRepository by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(appModules)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun `getCv return error if response is null`() = runBlocking {
        val service = declareMock<CvService> {
            given(getCv()).willReturn(Response.success(null))
        }


        val result = cvRepository.getCv()

        assertTrue(result is Result.Error)

        Mockito.verify(service, times(1)).getCv()
        return@runBlocking
    }

    @Test
    fun `getCv return error if response is error`() = runBlocking {
        val service = declareMock<CvService> {
            given(getCv()).willReturn(Response.error(500, "error".toResponseBody()))
        }


        val result = cvRepository.getCv()

        assertTrue(result is Result.Error)

        Mockito.verify(service, times(1)).getCv()
        return@runBlocking
    }

    @Test
    fun `getCv return cv if response is successfully and `() = runBlocking {
        val service = declareMock<CvService> {
            given(getCv()).willReturn(
                Response.success(SUCCES_RESPONSE_CV)
            )
        }

        val result = cvRepository.getCv()

        assertTrue(result is Result.Success)

        val data = (result as Result.Success).data
        assertEquals(EXPECTED_REPOSITORY_CV, data)

        Mockito.verify(service, times(1)).getCv()
        return@runBlocking
    }

    companion object {
        val SUCCES_RESPONSE_CV = Cv(
            age = 1,
            birthDate = "29-10-2345",
            education = listOf(
                Education(
                    degree = "a",
                    fromDate = "from",
                    name = "school",
                    toDate = "to"
                )
            ),
            firstName = "John",
            secondName = "Knox",
            phoneNumber = "334 4566 433",
            languages = listOf(Language(level = "c2", name = "Polish")),
            experience = listOf(
                Experience(
                    fromDate = "01-01-1990",
                    name = "Company",
                    toDate = "now",
                    projects = listOf(
                        Project(
                            description = "Simple project",
                            name = "Roundo",
                            technologies = listOf("Java")
                        )
                    )
                )
            ),
            trainings = listOf("Short training")
        )

        val EXPECTED_REPOSITORY_CV = RepositoryCv(
            age = 1,
            birthDate = "29-10-2345",
            education = listOf(
                RepositoryEducation(
                    degree = "a",
                    fromDate = "from",
                    name = "school",
                    toDate = "to"
                )
            ),
            firstName = "John",
            secondName = "Knox",
            phoneNumber = "334 4566 433",
            languages = listOf(RepositoryLanguage(level = "c2", name = "Polish")),
            experience = listOf(
                RepositoryExperience(
                    fromDate = "01-01-1990",
                    name = "Company",
                    toDate = "now",
                    projects = listOf(
                        RepositoryProject(
                            description = "Simple project",
                            name = "Roundo",
                            technologies = listOf("Java")
                        )
                    )
                )
            ),
            trainings = listOf("Short training")

        )
    }
}