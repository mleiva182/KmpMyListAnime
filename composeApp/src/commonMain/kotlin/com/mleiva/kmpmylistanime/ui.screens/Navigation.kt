package com.mleiva.kmpmylistanime.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mleiva.kmpmylistanime.data.AnimesRepository
import com.mleiva.kmpmylistanime.data.AnimesService
import com.mleiva.kmpmylistanime.data.database.AnimesDao
import com.mleiva.kmpmylistanime.ui.screens.detail.DetailScreen
import com.mleiva.kmpmylistanime.ui.screens.detail.DetailViewModel
import com.mleiva.kmpmylistanime.ui.screens.home.HomeScreen
import com.mleiva.kmpmylistanime.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime.ui.screens
 * Creted by: Marcelo Leiva on 28-07-2024 at 10:38
 ***/
@Composable
fun Navigation(animesDao: AnimesDao, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val repository = rememberAnimeRepository(animesDao)

    NavHost(navController = navController, startDestination = "home"){
        composable("home"){
            HomeScreen(
                onAnimeClick = { anime ->
                    navController.navigate("details/${anime.id}")
                },
                vm = viewModel {
                    HomeViewModel(repository)
                }
            )
        }
        composable(
            route ="details/{animeId}",
            arguments = listOf(navArgument("animeId"){ type = NavType.IntType })
        ){ backStackEntry ->
            val animeId = checkNotNull(backStackEntry.arguments?.getInt("animeId"))
            DetailScreen(
                vm = viewModel { DetailViewModel(animeId, repository) },
                onBack = { navController.popBackStack() }
            )
        }
    }

}

@Composable
private fun rememberAnimeRepository(
    animesDao: AnimesDao
): AnimesRepository = remember {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                }
            )
        }
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.jikan.moe"
            }
        }
    }
    AnimesRepository(AnimesService(client), animesDao)
}