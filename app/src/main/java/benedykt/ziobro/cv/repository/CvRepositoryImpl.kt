package benedykt.ziobro.cv.repository

import benedykt.ziobro.cv.api.service.CvService
import benedykt.ziobro.cv.repository.mapper.toRepositoryCv
import org.koin.core.KoinComponent
import org.koin.core.inject
import benedykt.ziobro.cv.repository.model.Cv as RepositoryCv

class CvRepositoryImpl : CvRepository, KoinComponent {

    private val cvService: CvService by inject()

    override suspend fun getCv(): Result<RepositoryCv> {
        return try {
            val response = cvService.getCv()

            if (response.isSuccessful) {
                response.body()?.let { cvResponse ->
                    Result.Success(cvResponse.toRepositoryCv())
                } ?: Result.Error("Empty body")
            } else {
                Result.Error(response.message())
            }

        } catch (e: Exception) {
            Result.Error(e.toString())
        }
    }
}