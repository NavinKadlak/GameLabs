package com.nsk.gamelabs.core.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.nsk.gamelabs.core.data.remote.model.TopGameEntity
import com.nsk.gamelabs.core.presentation.viewModel.HomeScreenViewModel.ChipUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    private val _gameData = MutableStateFlow<TopGameEntity.Result?>(null)
    val gameData: StateFlow<TopGameEntity.Result?> = _gameData


    fun setGameData(gameData : TopGameEntity.Result){
     _gameData.value = gameData
    }
}