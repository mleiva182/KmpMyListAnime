package com.mleiva.kmpmylistanime.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mleiva.kmpmylistanime.data.Anime
import com.mleiva.kmpmylistanime.ui.screens.Screen
import com.mleiva.kmpmylistanime.ui.screens.common.LoadingIndicator
import kmpmylistanime.composeapp.generated.resources.Res
import kmpmylistanime.composeapp.generated.resources.back
import org.jetbrains.compose.resources.stringResource

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime.ui.screens.detail
 * Creted by: Marcelo Leiva on 27-07-2024 at 14:05
 ***/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewModel, onBack: () -> Unit, modifier: Modifier = Modifier) {

    val state = vm.state


    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(state.anime?.name ?: "") },
                    navigationIcon = {
                        IconButton(onClick = onBack ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(Res.string.back)
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->

            LoadingIndicator(state.isLoading)

            state.anime?.let { anime ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                ) {
                    AsyncImage(
                        model = anime.images.jpg.imageUrl,
                        contentDescription = anime.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                            .aspectRatio(16f / 9f)
                    )
                    Text(
                        text = anime.synopsis,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }

        }
    }
}