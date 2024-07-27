package com.mleiva.kmpmylistanime

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime
 * Creted by: Marcelo Leiva on 27-07-2024 at 11:06
 ***/
data class Anime(
    val id: Int,
    val title: String,
    val image: String
)

val animeList = (1 .. 100 ).map {
    Anime(
        id = it,
        title = "Anime $it",
        image = "https://picsum.photos/200/300?id=$it"
    )
}
