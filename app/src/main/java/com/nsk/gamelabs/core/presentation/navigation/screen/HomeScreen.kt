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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nsk.gamelabs.core.data.remote.model.GenresEntity
import com.nsk.gamelabs.core.presentation.viewModel.HomeScreenViewModel
import com.nsk.gamelabs.ui.theme.DarkerGrey

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
                    Column {
                        Box(modifier = Modifier.padding()) {
                            Greeting(
                                name = "GameLabs",
                                modifier = Modifier.padding(20.dp,20.dp,0.dp,0.dp)
                            )
                        }
                        Box(modifier = Modifier.padding(10.dp,0.dp,10.dp,0.dp)) {

                            val state by viewModel.chipUiState.collectAsState()


                            GameCategory(state.chips,
                                selectedIndex = state.selectedIndex,
                                onItemClick = { index -> viewModel.onChipSelected(index) })                        }
                    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        fontSize = 28.sp,
        modifier = modifier
    )
}

@Composable
fun GameCategory(
    itemList: List<GenresEntity.Result>,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit = {}

) {

    LazyRow  {

        itemsIndexed(itemList) { index, item ->
            val isSelected = index == selectedIndex

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) Color.Red else DarkerGrey
                ),
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { onItemClick(index) },



            ){
                Text(
                    text = item.name,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()

                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GameLabsTheme {
        Greeting("Screen")
    }
}

