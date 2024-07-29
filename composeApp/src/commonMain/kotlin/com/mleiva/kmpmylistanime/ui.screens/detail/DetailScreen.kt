package com.mleiva.kmpmylistanime.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.mleiva.kmpmylistanime.data.Anime
import com.mleiva.kmpmylistanime.ui.screens.Screen
import com.mleiva.kmpmylistanime.ui.screens.common.LoadingIndicator
import kmpmylistanime.composeapp.generated.resources.Res
import kmpmylistanime.composeapp.generated.resources.back
import kmpmylistanime.composeapp.generated.resources.favorite
import kmpmylistanime.composeapp.generated.resources.rating
import kmpmylistanime.composeapp.generated.resources.status
import kmpmylistanime.composeapp.generated.resources.studio
import kmpmylistanime.composeapp.generated.resources.synopsis
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
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Screen {
        Scaffold(
            topBar = {
                DetailTopAppBar(state.anime?.name ?: "", onBack, scrollBehavior)
            },
            floatingActionButton = {
                state.anime?.let { anime ->
                    FloatingActionButton(onClick = vm::onFavoriteClick) {
                        Icon(
                            imageVector = if (anime.favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(Res.string.favorite)
                        )
                    }
                }
            },
        ) { paddingValues ->

            LoadingIndicator(state.isLoading, modifier = Modifier.padding(paddingValues))

            state.anime?.let { anime ->
                AnimeDetail(modifier = Modifier.padding(paddingValues), anime)
            }

        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailTopAppBar(
    title: String,
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(Res.string.back)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun AnimeDetail(
    modifier: Modifier = Modifier,
    anime: Anime
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {

        AsyncImage(
            model = anime.images.jpg.imageUrl,
            contentDescription = anime.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
        )

        Card(
            modifier = Modifier
                .padding(15.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(20.dp)
        ){
            Column(
                modifier = Modifier.padding(top = 7.dp, start = 20.dp)
            ) {
                Row {
                    Text(
                        text = stringResource(Res.string.status),
                        modifier = Modifier.padding(4.dp),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = anime.status,
                        modifier = Modifier.padding(4.dp),
                        fontSize = 12.sp
                    )
                }
                Row {
                    Text(
                        text = stringResource(Res.string.rating),
                        modifier = Modifier.padding(4.dp),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = anime.rating,
                        modifier = Modifier.padding(4.dp),
                        fontSize = 12.sp
                    )
                }
                Row {
                    Text(
                        text = stringResource(Res.string.studio),
                        modifier = Modifier.padding(4.dp),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = anime.studios.get(0).name,
                        modifier = Modifier.padding(4.dp),
                        fontSize = 12.sp
                    )
                }
                Row {
                    Text(
                        text = stringResource(Res.string.synopsis),
                        modifier = Modifier.padding(4.dp),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = anime.synopsis,
                        modifier = Modifier.padding(4.dp),
                        fontSize = 12.sp
                    )
                }

            }
        }
    }
}