package `in`.jadu.spectateur.viewImage.models.remote.api

import `in`.jadu.spectateur.viewImage.models.remote.dtos.ImageListDto
import retrofit2.Response
import retrofit2.http.GET

interface ImageGalleryApi {

    @GET("photos")
    suspend fun getImageList():Response<ImageListDto>
}