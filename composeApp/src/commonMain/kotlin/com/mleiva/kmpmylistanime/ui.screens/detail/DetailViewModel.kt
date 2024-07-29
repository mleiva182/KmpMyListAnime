package com.mleiva.kmpmylistanime.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.kmpmylistanime.data.Anime
import com.mleiva.kmpmylistanime.data.AnimesRepository
import com.mleiva.kmpmylistanime.ui.screens.home.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _state = MutableStateFlow(Uistate())
    val state: StateFlow<Uistate> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = Uistate(isLoading = true)
            repository.fetchAnimeById(id).collect{
                it?.let{
                    _state.value = Uistate(isLoading = false, anime = it)
                }
            }
        }
    }

    data class Uistate(
        val isLoading: Boolean = false,
        val anime: Anime? = null
    )

    fun onFavoriteClick(){
        state.value.anime?.let {
            viewModelScope.launch {
                repository.toggleFavorite(it)
            }

        }
    }

}