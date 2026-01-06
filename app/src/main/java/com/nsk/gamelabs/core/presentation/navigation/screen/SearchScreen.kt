package com.nsk.gamelabs.core.presentation.navigation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nsk.gamelabs.core.presentation.viewModel.SearchViewModel

@Composable
fun SearchScreen (
    viewModel: SearchViewModel = hiltViewModel(),
navController: NavHostController
) {
    Column {
        Box(modifier = Modifier.padding()) {
            Greeting(
                name = "Search",
                modifier = Modifier.padding(20.dp,20.dp,0.dp,0.dp)
            )
        }

    }


}




