package com.mleiva.kmpmylistanime.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime.data
 * Creted by: Marcelo Leiva on 28-07-2024 at 11:15
 ***/
@Serializable
data class RemoteResult(
    @SerialName("data") val results: List<Data>
)

@Serializable
data class RemoteResultData(
    @SerialName("data") val results: Data
) {
    fun toDomainModel(): Anime{
        return Anime(
            id = results.malId,
            images = results.images,
            name = results.title,
            episodes = results.episodes,
            status = results.status,
            rating = results.rating,
            synopsis = results.synopsis,
            broadcast = results.broadcast,
            genres = results.genres,
            studios = results.studios,
            favorite = false
        )
    }
}

@Serializable
data class Data(
    @SerialName("mal_id")
    val malId: Int = 0,
    @SerialName("images")
    val images: Images = Images(Jpg()),
    @SerialName("title")
    val title: String = "",
    @SerialName("episodes")
    val episodes: Int = 0,
    @SerialName("status")
    val status: String = "",
    @SerialName("rating")
    val rating: String = "",
    @SerialName("synopsis")
    val synopsis: String = "",
    @SerialName("broadcast")
    val broadcast: Broadcast = Broadcast(),
    @SerialName("genres")
    val genres: List<Genre> = emptyList(),
    @SerialName("studios")
    val studios: List<Studio> = emptyList(),
)

@Serializable
data class Broadcast(
    @SerialName("day")
    val day: String = "",
    @SerialName("time")
    val time: String = "",
    @SerialName("timezone")
    val timeZone: String = "",
    @SerialName("string")
    val string: String = ""
)

@Serializable
data class Genre(
    @SerialName("mal_id")
    val malId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String
)

@Serializable
data class Studio(
    @SerialName("mal_id")
    val malId: Int,
    @SerialName("type")
    val type: String,
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)

@Serializable
data class Images(
    @SerialName("jpg")
    val jpg: Jpg
)

@Serializable
data class Jpg(
    @SerialName("image_url")
    val imageUrl: String = "",
    @SerialName("large_image_url")
    val largeImageUrl: String = "",
    @SerialName("small_image_url")
    val smallImageUrl: String = ""
)