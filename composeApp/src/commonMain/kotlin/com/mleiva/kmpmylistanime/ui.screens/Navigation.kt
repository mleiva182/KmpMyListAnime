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
import io.ktor.http.parametersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime.ui.screens
 * Creted by: Marcelo Leiva on 28-07-2024 at 10:38
 ***/
@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home"){
        composable("home"){
            HomeScreen(
                onAnimeClick = { anime ->
                    navController.navigate("details/${anime.id}")
                }
            )
        }
        composable(
            route ="details/{animeId}",
            arguments = listOf(navArgument("animeId"){ type = NavType.IntType })
        ){ backStackEntry ->
            val animeId = checkNotNull(backStackEntry.arguments?.getInt("animeId"))
            DetailScreen(
                vm = koinViewModel(parameters = { parametersOf(animeId) }),
                onBack = { navController.popBackStack() }
            )
        }
    }

}