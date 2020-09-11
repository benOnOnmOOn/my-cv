package benedykt.ziobro.cv.api.service

import benedykt.ziobro.cv.api.model.Cv
import retrofit2.Response
import retrofit2.http.GET

interface CvService {

    @GET("b/5f5b3643ad23b57ef91011c2")
    suspend fun getCv(): Response<Cv>
}