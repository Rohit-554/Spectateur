package `in`.jadu.spectateur.viewImage.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.jadu.spectateur.viewImage.models.remote.dtos.ImageItem
import `in`.jadu.spectateur.viewImage.models.remote.dtos.ImageListDto
import `in`.jadu.spectateur.viewImage.models.remote.repository.imagegallery.ImageGalleryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageGalleryViewModel @Inject constructor(private val imageGalleryRepository: ImageGalleryRepository) : ViewModel() {
    private val mainEventChannel = Channel<MainEvent>()
    val mainEvent = mainEventChannel.receiveAsFlow()
    private val _getImageList = MutableLiveData<List<ImageItem>>()
    val getImageList: MutableLiveData<List<ImageItem>>
        get() = _getImageList

    init {
        setImageList()
    }

    fun setImageList() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = imageGalleryRepository.getImageList()
            if(response.isSuccessful){
                Log.d("response", "setImageList: ${response.body()}")
                _getImageList.postValue(response.body())
                viewModelScope.launch(Dispatchers.Main) {
                    mainEventChannel.send(MainEvent.Success("Success"))
                }
            }
        } catch (e: Exception) {
            Log.d("response", "setImageList: ${e.message}")
            viewModelScope.launch(Dispatchers.Main) {
                mainEventChannel.send(MainEvent.Error(e.message.toString()))
            }
        }
    }

    sealed class MainEvent {
        data class Error(val error: String) : MainEvent()
        data class Success(val message: String) : MainEvent()
    }

}
