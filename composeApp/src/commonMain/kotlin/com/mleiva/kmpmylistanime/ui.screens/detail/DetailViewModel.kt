package com.mleiva.kmpmylistanime.ui.screens.detail

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
 * From: com.mleiva.kmpmylistanime.ui.screens.detail
 * Creted by: Marcelo Leiva on 28-07-2024 at 14:27
 ***/
class DetailViewModel(
    private val id: Int,
    private val repository: AnimesRepository
): ViewModel() {

    var state by mutableStateOf(Uistate())
        private set

    init {
        viewModelScope.launch {
            state = Uistate(isLoading = true)
            repository.fetchAnimeById(id).collect{
                it?.let{
                    state = Uistate(isLoading = false, anime = it)
                }
            }
        }
    }

    data class Uistate(
        val isLoading: Boolean = false,
        val anime: Anime? = null
    )

    fun onFavoriteClick(){
        state.anime?.let {
            viewModelScope.launch {
                repository.toggleFavorite(it)
            }

        }
    }

}