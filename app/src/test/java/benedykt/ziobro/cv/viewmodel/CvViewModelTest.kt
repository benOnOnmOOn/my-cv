package benedykt.ziobro.cv.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import benedykt.ziobro.cv.TestCoroutineRule
import benedykt.ziobro.cv.di.appModules
import benedykt.ziobro.cv.repository.CvRepository
import benedykt.ziobro.cv.repository.Result
import benedykt.ziobro.cv.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.inject
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.*
import org.mockito.BDDMockito.given
import benedykt.ziobro.cv.repository.model.Cv as RepositoryModelCv
import benedykt.ziobro.cv.repository.model.Education as RepositoryModelEducation
import benedykt.ziobro.cv.repository.model.Experience as RepositoryModelExperience
import benedykt.ziobro.cv.repository.model.Language as RepositoryModelLanguage
import benedykt.ziobro.cv.repository.model.Project as RepositoryModelProject
import benedykt.ziobro.cv.viewmodel.model.Cv as ViewmodelModelCv
import benedykt.ziobro.cv.viewmodel.model.Education as ViewmodelModelEducation
import benedykt.ziobro.cv.viewmodel.model.Experience as ViewmodelModelExperience
import benedykt.ziobro.cv.viewmodel.model.Language as ViewmodelModelLanguage
import benedykt.ziobro.cv.viewmodel.model.Project as ViewmodelModelProject

@ExperimentalCoroutinesApi
class CvViewModelTest : KoinTest {

    val cvViewModel: CvViewModel by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(appModules)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observerCv: Observer<ViewmodelModelCv>

    @Mock
    lateinit var observerLoading: Observer<Boolean>

    @Mock
    lateinit var observerError: Observer<Event<Boolean>>

    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<Boolean>

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `fetchData  without errors - check if all observer get correct data `() {
        testCoroutineRule.runBlockingTest {
            declareMock<CvRepository> {
                given(getCv()).willReturn(Result.Success(SUCCES_RESPONSE_CV))
            }

            cvViewModel.isLoading.observeForever(observerLoading)
            cvViewModel.cv.observeForever(observerCv)
            cvViewModel.isError.observeForever(observerError)
            delay(500)

            Mockito.verify(observerLoading, Mockito.times(2))
                .onChanged(argumentCaptor.capture())
            Assert.assertTrue(argumentCaptor.allValues[0])
            Assert.assertFalse(argumentCaptor.allValues[1])
            Mockito.verify(observerError).onChanged(Event(false))
            Mockito.verify(observerCv).onChanged(EXPECTED_CV)
        }
    }

    @Test
    fun `fetchData  with errors - check if all observer get correct data `() {
        testCoroutineRule.runBlockingTest {
            declareMock<CvRepository> {
                given(getCv()).willReturn(Result.Error("Sample error"))
            }

            cvViewModel.isLoading.observeForever(observerLoading)
            cvViewModel.isError.observeForever(observerError)
            delay(500)
            Mockito.verify(observerLoading).onChanged(false)
            Mockito.verify(observerError).onChanged(Event(true))
        }
    }

    companion object {
        val SUCCES_RESPONSE_CV = RepositoryModelCv(
            age = 1,
            birthDate = "29-10-2345",
            education = listOf(
                RepositoryModelEducation(
                    degree = "a",
                    fromDate = "from",
                    name = "school",
                    toDate = "to"
                )
            ),
            firstName = "John",
            secondName = "Knox",
            phoneNumber = "334 4566 433",
            languages = listOf(
                RepositoryModelLanguage(
                    level = "c2",
                    name = "Polish"
                )
            ),
            experience = listOf(
                RepositoryModelExperience(
                    fromDate = "01-01-1990",
                    name = "Company",
                    toDate = "now",
                    projects = listOf(
                        RepositoryModelProject(
                            description = "Simple project",
                            name = "Roundo",
                            technologies = listOf("Java")
                        )
                    )
                )
            ),
            trainings = listOf("Short training")
        )

        val EXPECTED_CV = ViewmodelModelCv(
            age = 1,
            birthDate = "29-10-2345",
            education = listOf(
                ViewmodelModelEducation(
                    degree = "a",
                    fromDate = "from",
                    name = "school",
                    toDate = "to"
                )
            ),
            firstName = "John",
            secondName = "Knox",
            phoneNumber = "334 4566 433",
            languages = listOf(
                ViewmodelModelLanguage(
                    level = "c2",
                    name = "Polish"
                )
            ),
            experience = listOf(
                ViewmodelModelExperience(
                    fromDate = "01-01-1990",
                    name = "Company",
                    toDate = "now",
                    projects = listOf(
                        ViewmodelModelProject(
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