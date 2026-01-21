package com.nsk.gamelabs.core.data.repository

import com.nsk.gamelabs.core.data.remote.api.ApiService
import com.nsk.gamelabs.core.data.remote.model.GenresEntity
import com.nsk.gamelabs.core.domain.repository.GameRepository
import jakarta.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: ApiService
) : GameRepository{

    override suspend fun getGenres(): GenresEntity {
        return api.getGenres("ef87fc10f7ad4297a0b202cdf7da7af6")
    }


}