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
                            GameCategory()                        }
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
    onItemClick: (String) -> Unit = {}
) {
    val itemList = List(10) { index -> "Item ${index + 1}" }

    LazyRow  {
        items(itemList) { item ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = DarkerGrey
                ),
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { onItemClick(item) },



            ){
                Text(
                    text = item,
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

