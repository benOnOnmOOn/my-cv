package benedykt.ziobro.cv.repository

import benedykt.ziobro.cv.repository.model.Cv

interface CvRepository {
    suspend fun getCv(): Result<Cv>
}