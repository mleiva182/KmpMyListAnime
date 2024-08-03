package com.mleiva.kmpmylistanime.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime.data.database
 * Creted by: Marcelo Leiva on 28-07-2024 at 16:51
 ***/
 fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<AnimesDatabase>{
     val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder(
        context = appContext,
        name = dbFile.absolutePath
    )
 }