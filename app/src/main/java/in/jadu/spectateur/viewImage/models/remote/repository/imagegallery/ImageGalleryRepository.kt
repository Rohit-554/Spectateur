package `in`.jadu.spectateur.viewImage.models.remote.repository.imagegallery

import `in`.jadu.spectateur.viewImage.models.remote.api.ImageGalleryApi
import javax.inject.Inject

class ImageGalleryRepository @Inject constructor(private val imageGalleryApi: ImageGalleryApi) {

    suspend fun getImageList() = imageGalleryApi.getImageList()
}