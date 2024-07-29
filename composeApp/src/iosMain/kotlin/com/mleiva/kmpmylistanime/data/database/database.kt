package com.mleiva.kmpmylistanime.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime.data.database
 * Creted by: Marcelo Leiva on 28-07-2024 at 16:54
 ***/
 fun getDatabaseBuilder(): RoomDatabase.Builder<AnimesDatabase>{
     val dbFilePath = NSHomeDirectory() + "/$DATABASE_NAME"
     return Room.databaseBuilder<AnimesDatabase>(
         name = dbFilePath,
         factory = { AnimesDatabase::class.instanceImpl() }
     ).setDriver(BundledSQLiteDriver())
 }