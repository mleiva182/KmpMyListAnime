package com.mleiva.kmpmylistanime

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime
 * Creted by: Marcelo Leiva on 29-07-2024 at 11:57
 ***/
class AnimesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AnimesApp)
        }
    }

}