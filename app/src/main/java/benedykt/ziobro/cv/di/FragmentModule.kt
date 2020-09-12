package benedykt.ziobro.cv.di

import benedykt.ziobro.cv.fragment.CvFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

val fragmentModule = module {
    fragment { CvFragment() }
}