package com.nsk.gamelabs.core.domain.repository

interface GameRepository {
    fun getChips(): List<String> {
        val itemList = List(10) { index -> "Item ${index + 1}" }

        return itemList
    }


    fun getGameListBasedOnCategoryID(gameId : Int): List<String> {
        val itemList = List(10) { index -> "Item ${index + 1}" }

        return itemList
    }

    fun getTopGameList(): List<String> {
        val itemList = List(10) { index -> "Item ${index + 1}" }

        return itemList
    }
}