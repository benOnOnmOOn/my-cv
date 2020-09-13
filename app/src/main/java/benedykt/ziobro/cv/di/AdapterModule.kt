package benedykt.ziobro.cv.di

import benedykt.ziobro.cv.adapter.CvAdapter
import benedykt.ziobro.cv.repository.CvRepository
import benedykt.ziobro.cv.repository.CvRepositoryImpl
import org.koin.dsl.module

val adapterModule = module {
    factory { CvAdapter() }
}