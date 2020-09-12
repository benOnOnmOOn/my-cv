package benedykt.ziobro.cv.di

import benedykt.ziobro.cv.BuildConfig
import benedykt.ziobro.cv.api.service.CvService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.jsonbin.io/"

val apiModule = module {

    single {
        Retrofit
            .Builder()
            .client(get<OkHttpClient>())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        HttpLoggingInterceptor().apply {
            setLevel(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            )
        }
    }

    single<CvService> { get<Retrofit>().create(CvService::class.java) }

}