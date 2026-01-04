package com.nsk.gamelabs.core.presentation.navigation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nsk.gamelabs.ui.theme.GameLabsTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nsk.gamelabs.core.presentation.viewModel.HomeScreenViewModel
import com.nsk.gamelabs.core.presentation.viewModel.LibraryViewModel
import com.nsk.gamelabs.ui.theme.DarkerGrey

@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel = hiltViewModel(),
    navController: NavHostController
) {
                    Column {
                        Box(modifier = Modifier.padding()) {
                            Greeting(
                                name = "Library",
                                modifier = Modifier.padding(20.dp,20.dp,0.dp,0.dp)
                            )
                        }

                    }


}





