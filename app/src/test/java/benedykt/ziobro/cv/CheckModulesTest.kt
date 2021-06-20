package benedykt.ziobro.cv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import benedykt.ziobro.cv.di.appModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.test.KoinTest
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@Category(CheckModuleTest::class)
class CheckModulesTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun checkAllModules() = testCoroutineRule.runBlockingTest {
        checkModules {
            modules(appModules)
        }
    }
}
