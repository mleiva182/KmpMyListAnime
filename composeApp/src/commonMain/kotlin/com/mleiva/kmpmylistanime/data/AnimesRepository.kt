package com.mleiva.kmpmylistanime.data

import com.mleiva.kmpmylistanime.data.database.AnimesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime.data
 * Creted by: Marcelo Leiva on 28-07-2024 at 14:19
 ***/
class AnimesRepository(
    private val animesService: AnimesService,
    private val animesDao: AnimesDao
) {

    val animes: Flow<List<Anime>> = animesDao.fetchAnimes().onEach { animes ->
        if(animes.isEmpty()){
            val remoteAnimes = animesService.fetchAnimes().results.map {
                it.toDomainModel()
            }
            animesDao.save(remoteAnimes)
        }
    }


    suspend fun fetchAnimeById(id: Int): Flow<Anime?> = animesDao.fetchAnimeById(id).onEach { anime ->
        if(anime == null) {
            val remoteAnime = animesService.fetchAnimeById(id).toDomainModel()
            animesDao.save(listOf(remoteAnime))
        }
    }

    suspend fun toggleFavorite(anime: Anime) {
        animesDao.save(listOf(anime.copy(favorite = !anime.favorite)))
    }

}

private fun Data.toDomainModel() = Anime(
    id = malId,
    images = images,
    name = title,
    episodes = episodes,
    status = status,
    rating = rating,
    synopsis = synopsis,
    broadcast = broadcast,
    genres = genres,
    studios = studios,
    favorite = false
)