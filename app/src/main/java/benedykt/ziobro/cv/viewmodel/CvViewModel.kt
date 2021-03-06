package benedykt.ziobro.cv.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import benedykt.ziobro.cv.repository.CvRepository
import benedykt.ziobro.cv.repository.Result
import benedykt.ziobro.cv.utils.Event
import benedykt.ziobro.cv.viewmodel.mapper.toViewModelCv
import benedykt.ziobro.cv.viewmodel.model.Cv
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class CvViewModel : ViewModel(), KoinComponent {

    private val cvRepository: CvRepository by inject()

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading


    private val _isError = MutableSharedFlow<Boolean>()
    val isError: SharedFlow<Boolean> = _isError

    private val _cv = MutableLiveData<Cv>()
    val cv: LiveData<Cv> = _cv

    init {
        fetchData()
    }

    private fun fetchData() {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = cvRepository.getCv()
            _isLoading.postValue(false)
            when (result) {
                is Result.Success -> {
                    val data = result.data
                    _cv.postValue(data.toViewModelCv())
                }
                is Result.Error -> {
                    _isError.emit(true)
                }
            }
        }
    }

}