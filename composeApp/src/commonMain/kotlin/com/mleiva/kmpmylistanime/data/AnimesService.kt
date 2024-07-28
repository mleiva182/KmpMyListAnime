package com.mleiva.kmpmylistanime.data

import androidx.compose.runtime.rememberUpdatedState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime.data
 * Creted by: Marcelo Leiva on 28-07-2024 at 11:29
 ***/
class AnimesService(private val client: HttpClient) {

    suspend fun fetchAnimes(): RemoteResult{
        return client.get("/v4/anime")
            .body<RemoteResult>()
    }

    suspend fun fetchAnimeById(id: Int): RemoteResultData{
        return client
            .get("/v4/anime/$id")
            .body<RemoteResultData>()
    }
}