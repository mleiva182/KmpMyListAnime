package com.mleiva.kmpmylistanime.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.kmpmylistanime.data.Anime
import com.mleiva.kmpmylistanime.data.AnimesRepository
import kotlinx.coroutines.launch

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime.ui.screens.home
 * Creted by: Marcelo Leiva on 28-07-2024 at 11:00
 ***/
class HomeViewModel(
    private val repository: AnimesRepository
) : ViewModel() {


    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(isLoading = true)
            repository.animes.collect{
                if(it.isNotEmpty()){
                    state = UiState(isLoading = false, animeList = it)
                }
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val animeList: List<Anime> = emptyList()
    )

}