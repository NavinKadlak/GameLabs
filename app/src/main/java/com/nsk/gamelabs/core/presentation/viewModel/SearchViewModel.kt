package com.nsk.gamelabs.core.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.nsk.gamelabs.core.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: GameRepository) : ViewModel() {
}