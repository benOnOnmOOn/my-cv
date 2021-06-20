package benedykt.ziobro.cv.di

import benedykt.ziobro.cv.adapter.CvAdapter
import benedykt.ziobro.cv.adapter.CvPageAdapter
import benedykt.ziobro.cv.repository.CvRepository
import benedykt.ziobro.cv.repository.CvRepositoryImpl
import benedykt.ziobro.cv.viewmodel.CvPagingDataSource
import org.koin.dsl.module

val adapterModule = module {
    factory { CvAdapter() }
    factory { CvPagingDataSource() }
    factory { CvPageAdapter() }
}