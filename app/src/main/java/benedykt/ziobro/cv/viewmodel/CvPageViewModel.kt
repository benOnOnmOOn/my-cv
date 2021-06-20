package benedykt.ziobro.cv.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import benedykt.ziobro.cv.adapter.CvItemModel
import benedykt.ziobro.cv.adapter.toCvItemModelList
import benedykt.ziobro.cv.repository.CvRepository
import benedykt.ziobro.cv.repository.Result
import benedykt.ziobro.cv.viewmodel.mapper.toViewModelCv
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.concurrent.TimeUnit

const val PAGE_SIZE = 5

class CvPageViewModel : ViewModel(), KoinComponent {

    private val cvPagingDataSource: CvPagingDataSource by inject()

    val cvs: Flow<PagingData<CvItemModel>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { cvPagingDataSource }
        ).flow
            .cachedIn(viewModelScope)

}

class CvPagingDataSource : PagingSource<Int, CvItemModel>(), KoinComponent {

    private val cvRepository: CvRepository by inject()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CvItemModel> {
        val pageNumber = params.key ?: 1
        delay(TimeUnit.SECONDS.toMillis(2))
        Log.i("Paging", "Loading page $pageNumber")
        return when (val cv = cvRepository.getCv()) {
            is Result.Error -> LoadResult.Error(IllegalStateException(cv.errorMessage))
            is Result.Success -> {
                val list = cv.data
                    .toViewModelCv()
                    .toCvItemModelList()

                val nextPageNumber =
                    if (pageNumber * PAGE_SIZE < list.size)
                        pageNumber + 1
                    else
                        null

                Log.i("Paging", "Loading nextPageNumber $nextPageNumber")
                val toIndex = if (pageNumber * PAGE_SIZE < list.size)
                    pageNumber * PAGE_SIZE
                else
                    list.size

                Log.i("Paging", "Loading toIndex $toIndex")
                LoadResult.Page(
                    data = list.subList((pageNumber - 1) * PAGE_SIZE, toIndex),
                    prevKey = null,
                    nextKey = nextPageNumber
                )
            }
        }

    }

    override fun getRefreshKey(state: PagingState<Int, CvItemModel>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

}