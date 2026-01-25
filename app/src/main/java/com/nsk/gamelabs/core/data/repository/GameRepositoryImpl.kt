package com.nsk.gamelabs.core.data.repository

import com.nsk.gamelabs.core.data.remote.api.ApiService
import com.nsk.gamelabs.core.data.remote.model.GameDescriptionEntity
import com.nsk.gamelabs.core.data.remote.model.GenresEntity
import com.nsk.gamelabs.core.data.remote.model.TopGameEntity
import com.nsk.gamelabs.core.domain.repository.GameRepository
import jakarta.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: ApiService
) : GameRepository{

    override suspend fun getGenres(): GenresEntity {
        return api.getGenres("ef87fc10f7ad4297a0b202cdf7da7af6")
    }

    override suspend fun getTopGames(): TopGameEntity {
        return api.getTopGames(
            "ef87fc10f7ad4297a0b202cdf7da7af6",
                    ordering = "-added",
            exclude_additions = true)
    }

    override suspend fun getAllTimeBest(): TopGameEntity {
        return api.getBestOfAllTime(
            "ef87fc10f7ad4297a0b202cdf7da7af6",
            ordering = "-added",
            exclude_additions = true,
            metacritic = "100"
        )
    }

    override suspend fun getGameDetails(id : Int): GameDescriptionEntity {
        return api.getGameDetails(id,
            "ef87fc10f7ad4297a0b202cdf7da7af6")
    }


}