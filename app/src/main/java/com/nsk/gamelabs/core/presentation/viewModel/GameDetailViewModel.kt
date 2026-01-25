package com.nsk.gamelabs.core.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsk.gamelabs.core.data.remote.model.GameDescriptionEntity
import com.nsk.gamelabs.core.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Collections

@HiltViewModel
class GameDetailViewModel  @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

    private val _details = MutableStateFlow<GameDescriptionEntity?>(null)
    val details: StateFlow<GameDescriptionEntity?> = _details


    fun getDescription(id : Int){
        viewModelScope.launch {
            val data = repository.getGameDetails(id)
            _details.value = data

        }
    }
}