package com.nsk.gamelabs.core.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nsk.gamelabs.core.presentation.navigation.screen.GameViewScreen
import com.nsk.gamelabs.core.presentation.navigation.screen.HomeScreen
import com.nsk.gamelabs.core.presentation.navigation.screen.LibraryScreen
import com.nsk.gamelabs.core.presentation.navigation.screen.Routes
import com.nsk.gamelabs.core.presentation.navigation.screen.SearchScreen
import com.nsk.gamelabs.core.presentation.viewModel.GameDetailViewModel
import com.nsk.gamelabs.core.presentation.viewModel.HomeScreenViewModel
import com.nsk.gamelabs.core.presentation.viewModel.LibraryViewModel
import com.nsk.gamelabs.core.presentation.viewModel.SearchViewModel
import com.nsk.gamelabs.core.presentation.viewModel.SharedViewModel
import okhttp3.Route


// Main NavGraph function - Call this in Scaffold
    @Composable
    fun GameNavGraph(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    val sharedVM: SharedViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            if(shouldShowBottomBar(navController.currentBackStackEntryAsState().value?.destination)){
                BottomNavBar(navController)
            }
           },  // ← REQUIRED
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = ScreenBottomNav.Home.route,
            modifier = modifier.padding(paddingValues)
        ) {
            // Home Screen - Scoped ViewModel injection
            composable(ScreenBottomNav.Home.route) {
                val viewModel: HomeScreenViewModel = hiltViewModel()
                HomeScreen(
                    viewModel = viewModel,
                    sharedViewModel = sharedVM,
                    navController = navController
                )
            }

            // Bookmark Screen
            composable(ScreenBottomNav.Search.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(
                    viewModel = viewModel,
                    navController = navController
                )
            }
            // Bookmark Screen
            composable(ScreenBottomNav.Bookmark.route) {
                val viewModel: LibraryViewModel = hiltViewModel()
                LibraryScreen(
                    viewModel = viewModel,
                    navController = navController
                )
            }

            // Detail Game Screen
            composable(Routes.GameView.route) {
                val viewModel: GameDetailViewModel = hiltViewModel()
                GameViewScreen(
                    viewModel = viewModel,
                    sharedViewModel = sharedVM,
                    navController = navController
                )
            }
        }
    }
}

fun shouldShowBottomBar(destination: NavDestination?): Boolean {
    return when (destination?.route) {
        "home", "search", "bookmark" -> true
        else -> false  // Hide on detail/settings screens

    }
}




