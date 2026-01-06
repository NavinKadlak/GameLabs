package com.nsk.gamelabs.core.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsk.gamelabs.core.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeScreenViewModel  @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

    //val featuredGames = repository.getFeaturedGames().stateIn(...)
    private var currentLoadJob: Job? = null

    data class ChipUiState(
        val chips: List<String> = emptyList(),
        val selectedIndex: Int = 0,
        val isLoading : Boolean = false
    )

    private val _chipUiState = MutableStateFlow(ChipUiState())
    val chipUiState: StateFlow<ChipUiState> = _chipUiState


    data class TopGamesUiState(
        val topGames: List<String> = emptyList(),
        val selectedIndex: Int = 0,
        val isLoading : Boolean = false
    )

    private val _topGamesUiState = MutableStateFlow(TopGamesUiState())
    val topGamesUiState: StateFlow<TopGamesUiState> = _topGamesUiState

    init {
        // Load from repository / use case
        viewModelScope.launch {
            val chips = repository.getChips()
            _chipUiState.update { it.copy(chips = chips) }
        }
    }

    fun onChipSelected(index: Int) {
        _chipUiState.update { it.copy(selectedIndex = index) }

    }

    /*private fun loadDataForChip(index: Int) {
        currentLoadJob?.cancel()
        currentLoadJob = viewModelScope.launch {
            try {
                _chipUiState.update { it.copy(isLoading = true) }
               var chip = _chipUiState.value.chips.get(index)
                val domainGames = repository.getGameListBasedOnCategoryID(index)
                val uiGames = domainGames.map { it.toUiModel() }  // Extension/map
                _chipUiState.update {
                    it.copy(
                        games = uiGames,
                        isLoading = false
                    )
                }
            } catch (e: Exception) { *//* error *//* }
        }
    }*/

    fun loadTopGames(){

        viewModelScope.launch {
            try {
               val topGames = repository.getTopGameList()
                _topGamesUiState.update {
                    it.copy( topGames = topGames)
                }
            }
            catch (e : Exception){

            }
        }
    }
}