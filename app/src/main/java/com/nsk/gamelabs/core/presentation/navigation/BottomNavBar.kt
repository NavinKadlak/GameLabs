package com.nsk.gamelabs.core.presentation.navigation

import android.provider.CalendarContract.Colors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

    @Composable
    fun BottomNavBar(navController: NavHostController) {
        val items = listOf(
            ScreenBottomNav.Home,
            ScreenBottomNav.Library,
        )

        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        NavigationBar(containerColor = Color.Transparent ){
            items.forEach { screen ->
                NavigationBarItem(
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(
                        imageVector = screen.icon,
                        contentDescription = null
                    ) },
                    label = { Text(
                        text = "Home",
//                        color = Color.Black // Force color here to test visibility
                    )
                            },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White, // Selected label color
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray, // Unselected label color
                        indicatorColor = Color.DarkGray // The pill-shaped background behind the selected icon
                    ),
                    alwaysShowLabel = true
                )
            }
        }
    }
