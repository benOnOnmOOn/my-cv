package benedykt.ziobro.cv.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import benedykt.ziobro.cv.adapter.CvItemModel
import benedykt.ziobro.cv.adapter.toCvItemModelList
import benedykt.ziobro.cv.repository.CvRepository
import benedykt.ziobro.cv.repository.Result
import benedykt.ziobro.cv.viewmodel.mapper.toViewModelCv
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent
import org.koin.core.inject

const val PAGE_SIZE = 2

class CvPageViewModel : ViewModel(), KoinComponent {

    private val cvPagingDataSource: CvPagingDataSource by inject()

    val cvs: Flow<PagingData<CvItemModel>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 3),
            pagingSourceFactory = { cvPagingDataSource }
        ).flow
            .cachedIn(viewModelScope)

}

class CvPagingDataSource : PagingSource<Int, CvItemModel>(), KoinComponent {

    private val cvRepository: CvRepository by inject()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CvItemModel> {
        val pageNumber = params.key ?: 0
        return when (val cv = cvRepository.getCv()) {
            is Result.Error -> LoadResult.Error(IllegalStateException(cv.errorMessage))
            is Result.Success -> {
                val list = cv.data
                    .toViewModelCv()
                    .toCvItemModelList()

                val nextPageNumber =
                    if (list.size / PAGE_SIZE < pageNumber + 1)
                        pageNumber + 1
                    else
                        null
                val toIndex =  nextPageNumber?.let { it * PAGE_SIZE }?: list.size
                LoadResult.Page(
                    data = cv.data
                        .toViewModelCv()
                        .toCvItemModelList()
                        .subList(pageNumber * PAGE_SIZE, toIndex),
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