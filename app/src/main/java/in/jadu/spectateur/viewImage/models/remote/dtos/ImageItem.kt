package `in`.jadu.spectateur.viewImage.models.remote.dtos

data class ImageItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)