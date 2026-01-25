package com.nsk.gamelabs.core.data.remote.api

import com.nsk.gamelabs.core.data.remote.model.GameDescriptionEntity
import com.nsk.gamelabs.core.data.remote.model.GenresEntity
import com.nsk.gamelabs.core.data.remote.model.TopGameEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/genres")
    suspend fun getGenres(@Query("key") key: String): GenresEntity

 @GET("api/games")
    suspend fun getTopGames(@Query("key") key: String,
                            @Query("ordering") ordering : String,
                            @Query("exclude_additions") exclude_additions : Boolean,
                            ): TopGameEntity

 @GET("api/games")
    suspend fun getBestOfAllTime(@Query("key") key: String,
                                 @Query("ordering") ordering : String,
                                 @Query("exclude_additions") exclude_additions : Boolean,
                                 @Query("metacritic") metacritic : String
 ): TopGameEntity

    @GET("api/games/{id}")
    suspend fun getGameDetails(@Path("id") id: Int,
        @Query("key") key: String
    ): GameDescriptionEntity

}