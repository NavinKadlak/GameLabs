package com.nsk.gamelabs.core.data.remote.model

data class GenresEntity(
        val count: Long,
        val next: Any?,
        val previous: Any?,
        val results: List<Result>,
    ){

    data class Result(
        val id: Long,
        val name: String,
        val slug: String,
        val games_count: Long,
        val image_background: String,
        val games: List<Game>,
    )
    {
        data class Game(
            val id: Long,
            val slug: String,
            val name: String,
            val added: Long,
        )
    }


}



