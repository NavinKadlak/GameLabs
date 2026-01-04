package com.nsk.gamelabs.core.presentation.navigation

import android.graphics.drawable.Icon
import android.graphics.drawable.VectorDrawable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenBottomNav(val route: String, val title : String,val icon:ImageVector) {
    object Home : ScreenBottomNav("home","Home", Icons.Default.Home)
    object Library : ScreenBottomNav("library","Library",Icons.Default.Favorite)

}