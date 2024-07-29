package com.mleiva.kmpmylistanime.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mleiva.kmpmylistanime.data.Anime
import kotlinx.coroutines.flow.Flow

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime.data.database
 * Creted by: Marcelo Leiva on 28-07-2024 at 16:45
 ***/
@Dao
interface AnimesDao {

    @Query("SELECT * FROM Anime")
    fun fetchAnimes(): Flow<List<Anime>>

    @Query("SELECT * FROM Anime WHERE id = :id")
    fun fetchAnimeById(id: Int): Flow<Anime?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(anime: List<Anime>)
}