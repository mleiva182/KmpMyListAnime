package com.mleiva.kmpmylistanime.ui.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime.ui.screens
 * Creted by: Marcelo Leiva on 27-07-2024 at 13:59
 ***/
@Composable
fun Screen(modifier: Modifier = Modifier, content: @Composable () -> Unit){

    val isDarkMode = isSystemInDarkTheme()
    val appColorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()

    MaterialTheme(
        colorScheme = appColorScheme
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            content = content
        )
    }

}