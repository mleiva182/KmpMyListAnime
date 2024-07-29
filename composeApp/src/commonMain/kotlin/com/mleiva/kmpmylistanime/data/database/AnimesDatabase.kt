package com.mleiva.kmpmylistanime.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mleiva.kmpmylistanime.Converters
import com.mleiva.kmpmylistanime.data.Anime

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime.data.database
 * Creted by: Marcelo Leiva on 28-07-2024 at 16:48
 ***/
const val DATABASE_NAME = "animes.db"
interface DB{
    fun clearAllTables()
}

@Database(entities = [Anime::class], version = 1)
@TypeConverters(Converters::class)
abstract class AnimesDatabase: RoomDatabase(), DB {
    abstract fun animesDao(): AnimesDao

    override fun clearAllTables() {}
}