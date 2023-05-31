package `in`.jadu.spectateur.viewImage.hiltmodule

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `in`.jadu.spectateur.viewImage.models.remote.api.ImageGalleryApi
import `in`.jadu.spectateur.viewImage.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {


    @Provides
    @Singleton
    fun provideImageApiService():ImageGalleryApi = Retrofit.Builder().baseUrl(Constants.BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ImageGalleryApi::class.java)

}