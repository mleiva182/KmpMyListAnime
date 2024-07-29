package com.mleiva.kmpmylistanime

import androidx.compose.runtime.*
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.mleiva.kmpmylistanime.data.database.AnimesDao
import com.mleiva.kmpmylistanime.ui.screens.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(animesDao: AnimesDao) {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .crossfade(true)
            .logger(DebugLogger())
            .build()
    }
    Navigation(animesDao)
}
