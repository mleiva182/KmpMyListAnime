package com.mleiva.kmpmylistanime.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime
 * Creted by: Marcelo Leiva on 27-07-2024 at 11:06
 ***/
@Entity
data class Anime(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val images: Images,
    val name: String,
    val episodes: Int,
    val status: String,
    val rating: String,
    val synopsis: String,
    val broadcast: Broadcast,
    val genres: List<Genre>,
    val studios: List<Studio>,
    val favorite: Boolean
)

