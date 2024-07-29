package com.mleiva.kmpmylistanime

import androidx.room.RoomDatabase
import com.mleiva.kmpmylistanime.data.database.AnimesDao
import com.mleiva.kmpmylistanime.data.database.AnimesDatabase
import com.mleiva.kmpmylistanime.data.AnimesRepository
import com.mleiva.kmpmylistanime.data.AnimesService
import com.mleiva.kmpmylistanime.ui.screens.home.HomeViewModel
import com.mleiva.kmpmylistanime.ui.screens.detail.DetailViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime
 * Creted by: Marcelo Leiva on 29-07-2024 at 11:43
 ***/
val appModule = module {
    single<AnimesDao>{
        val dbBuilder = get<RoomDatabase.Builder<AnimesDatabase>>()
        dbBuilder.build().animesDao()
    }
}

val dataModule = module {
    factoryOf(::AnimesRepository)
    factoryOf(::AnimesService)
    single<HttpClient> {
        HttpClient {
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
    }
}

val viewModelsModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}

expect val nativeModuiule: Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule, dataModule, viewModelsModule, nativeModuiule)
    }
}

