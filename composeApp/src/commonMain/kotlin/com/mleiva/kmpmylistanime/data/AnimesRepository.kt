package com.mleiva.kmpmylistanime.data

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime.data
 * Creted by: Marcelo Leiva on 28-07-2024 at 14:19
 ***/
class AnimesRepository(private val animesService: AnimesService) {

    suspend fun fetchAnimes(): List<Anime> {
        return animesService.fetchAnimes().results.map { it.toDomainModel() }
    }

    suspend fun fetchAnimeById(id: Int): Anime{
        return animesService.fetchAnimeById(id).toDomainModel()
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