package com.nsk.gamelabs.core.data.remote.api

import com.nsk.gamelabs.core.data.remote.model.GenresEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/genres")
    suspend fun getGenres(@Query("key") userId: String): GenresEntity
}