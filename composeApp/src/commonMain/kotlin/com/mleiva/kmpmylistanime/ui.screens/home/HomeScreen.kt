package com.mleiva.kmpmylistanime.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mleiva.kmpmylistanime.data.Anime
import com.mleiva.kmpmylistanime.ui.screens.Screen
import com.mleiva.kmpmylistanime.ui.screens.common.LoadingIndicator
import kmpmylistanime.composeapp.generated.resources.Res
import kmpmylistanime.composeapp.generated.resources.app_name
import kmpmylistanime.composeapp.generated.resources.favorite
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime.ui.screens.home
 * Creted by: Marcelo Leiva on 27-07-2024 at 14:03
 ***/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onAnimeClick: (Anime) -> Unit = {},
               vm: HomeViewModel = koinViewModel(),
               modifier: Modifier = Modifier) {
    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(Res.string.app_name)) },
                    scrollBehavior = scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { padding ->

            val state = vm.state
            LoadingIndicator(state.isLoading,  modifier = Modifier.padding(padding))

            LazyVerticalGrid(
                modifier = Modifier.padding(padding),
                columns = GridCells.Adaptive(120.dp),
                contentPadding = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                items(state.animeList, key = {it.id}){
                    AnimeItem(it, onAnimeClick = {onAnimeClick(it)})
                }
            }
        }
    }
}


@Composable
fun AnimeItem(anime: Anime, onAnimeClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable(onClick = onAnimeClick)
    ) {
        Box {
            AsyncImage(
                model = anime.images.jpg.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2 / 3f)
                    .clip(MaterialTheme.shapes.small)
            )
            if (anime.favorite) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(Res.string.favorite),
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)
                )
            }
        }

        Text(
            text = anime.name,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            modifier = Modifier.padding(8.dp)
        )
    }
}