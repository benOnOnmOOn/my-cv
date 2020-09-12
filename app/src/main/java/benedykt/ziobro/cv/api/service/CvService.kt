package benedykt.ziobro.cv.api.service

import benedykt.ziobro.cv.api.model.Cv
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface CvService {

    @GET("b/5f5b3643ad23b57ef91011c2/1")
    @Headers("""secret-key: ${'$'}2b${'$'}10${'$'}6KkrVgzhDBoqmlfSIZLjjOY19OaThB8vIPpCly2ebDDUDA6IHvqVm""")
    suspend fun getCv(): Response<Cv>
}