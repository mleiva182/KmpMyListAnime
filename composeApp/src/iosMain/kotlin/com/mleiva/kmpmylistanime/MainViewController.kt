package com.mleiva.kmpmylistanime

import androidx.compose.ui.window.ComposeUIViewController
import com.mleiva.kmpmylistanime.data.database.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    App()
}