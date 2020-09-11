package benedykt.ziobro.cv.di

import benedykt.ziobro.cv.viewmodel.CvViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CvViewModel() }
}