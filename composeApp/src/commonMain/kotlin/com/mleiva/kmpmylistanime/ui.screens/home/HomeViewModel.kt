package com.mleiva.kmpmylistanime.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.kmpmylistanime.data.Anime
import com.mleiva.kmpmylistanime.data.AnimesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime.ui.screens.home
 * Creted by: Marcelo Leiva on 28-07-2024 at 11:00
 ***/
class HomeViewModel(
    private val repository: AnimesRepository
) : ViewModel() {


    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            repository.animes.collect{
                if(it.isNotEmpty()){
                    _state.value = UiState(isLoading = false, animeList = it)
                }
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val animeList: List<Anime> = emptyList()
    )

}