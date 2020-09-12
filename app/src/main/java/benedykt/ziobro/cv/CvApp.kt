package benedykt.ziobro.cv

import android.app.Application
import benedykt.ziobro.cv.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class CvApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CvApp)
            fragmentFactory()
            modules(appModules)
        }
    }
}