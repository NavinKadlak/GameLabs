package com.nsk.gamelabs.core.presentation.navigation.screen

sealed class Routes(val route: String,val title : String ) {
    object GameView : Routes("gameView","GameDetailView")
}