package com.nsk.gamelabs.core.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsk.gamelabs.core.data.remote.model.GenresEntity
import com.nsk.gamelabs.core.data.remote.model.TopGameEntity
import com.nsk.gamelabs.core.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Collections.emptyList

@HiltViewModel
class HomeScreenViewModel  @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

    //val featuredGames = repository.getFeaturedGames().stateIn(...)
    private var currentLoadJob: Job? = null

    data class ChipUiState(
        val chips: List<GenresEntity.Result> = emptyList() ,
        val selectedIndex: Int = -1,
        val isLoading : Boolean = false
    )

    data class TopUiState(
        val topGames: List<TopGameEntity.Result> = emptyList(),
        val selectedIndex: Int = -1,
        val isLoading : Boolean = false
    )

    private val _chipUiState = MutableStateFlow(ChipUiState())
    val chipUiState: StateFlow<ChipUiState> = _chipUiState


    private val _topGame = MutableStateFlow(TopUiState())
    val topGame: StateFlow<TopUiState> = _topGame

    private val _bestOfAllTime = MutableStateFlow(TopUiState())
    val bestOfAllTime: StateFlow<TopUiState> = _bestOfAllTime

    data class TopGamesUiState(
        val topGames: List<String> = emptyList(),
        val selectedIndex: Int = 0,
        val isLoading : Boolean = false
    )

    private val _topGamesUiState = MutableStateFlow(TopGamesUiState())
    val topGamesUiState: StateFlow<TopGamesUiState> = _topGamesUiState

    init {
        // Load from repository / use case
       /* viewModelScope.launch {
            val chips = repository.getChips()
            _chipUiState.update { it.copy(chips = chips) }
        }*/

        viewModelScope.launch {
            try {
              /*  val data = repository.getGenres()
                _chipUiState.update { it.copy(chips = data.results) }
*/
                val getGenre = async { repository.getGenres() }
                val getTopGame = async {  repository.getTopGames() }
                val getAllTimeBest = async { repository.getAllTimeBest() }

                val genre = getGenre.await()
                val topGame = getTopGame.await()
                val getAllTime = getAllTimeBest.await()

                _chipUiState.update { it.copy(chips = genre.results) }
                _topGame.update { it.copy(topGame.results) }
                _bestOfAllTime.update { it.copy(getAllTime.results) }
            }
            catch (e : Exception){

            }

        }
    }

    fun onChipSelected(index: Int) {
        _chipUiState.update { it.copy(selectedIndex = index) }

    }

}