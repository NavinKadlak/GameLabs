package com.nsk.gamelabs.core.presentation.navigation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nsk.gamelabs.ui.theme.GameLabsTheme
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.nsk.gamelabs.R
import com.nsk.gamelabs.core.data.remote.model.GenresEntity
import com.nsk.gamelabs.core.data.remote.model.TopGameEntity
import com.nsk.gamelabs.core.presentation.viewModel.HomeScreenViewModel
import com.nsk.gamelabs.core.presentation.viewModel.SharedViewModel
import com.nsk.gamelabs.ui.theme.Black
import com.nsk.gamelabs.ui.theme.DarkerGrey
import com.nsk.gamelabs.ui.theme.Grey

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val bestOfAllTimeState by viewModel.bestOfAllTime.collectAsState()


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        item {
            Greeting(
                name = "GameLabs",
                modifier = Modifier.padding(20.dp,10.dp,0.dp,0.dp)
            )
        }

        item {
            Box(modifier = Modifier.padding(0.dp,10.dp,10.dp,0.dp)) {

                val state by viewModel.chipUiState.collectAsState()


                GameCategory(state.chips,
                    selectedIndex = state.selectedIndex,
                    onItemClick = { index -> viewModel.onChipSelected(index)
                        navController.navigate(Routes.GameView.route)})


            }
        }

        item {
            TopGameUI(viewModel,navController,sharedViewModel)

        }

        item {

            Text(
                text = "Best of All Time",


                fontSize = 22.sp,
                modifier = Modifier.padding(20.dp, 10.dp, 0.dp, 10.dp)
            )
        }

        items(bestOfAllTimeState.topGames.size) { index ->
            val item = bestOfAllTimeState.topGames[index]
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(100.dp),
                onClick = {
                    sharedViewModel.setGameData(bestOfAllTimeState.topGames[index])
                    navController.navigate(Routes.GameView.route)
                          },
                colors = CardDefaults.cardColors(
                    containerColor = Grey

                )
                ){

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AsyncImage(
                        model = item.background_image,
                        contentDescription = item.name,
                        modifier = Modifier
                            .width(120.dp)
                            .fillMaxHeight()
                            .padding(3.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop,
                        /*             placeholder = painterResource(R.drawable.placeholder_game), // Add your placeholder
                                     error = painterResource(R.drawable.error_image)*/
                    )

                    Column(
                        modifier = Modifier
                            .width(150.dp)
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = item.name ,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            fontSize = 14.sp
                        )
                        Text(
                            text = item.genres[0].name ,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            fontSize = 12.sp
                        )
                    }

                    Text(
                        text = item.rating.toString() ,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        fontSize = 9.sp
                    )

                }

            }

        }
    }
                    /*Column {
                        Box(modifier = Modifier.padding()) {
                            Greeting(
                                name = "GameLabs",
                                modifier = Modifier.padding(20.dp,10.dp,0.dp,0.dp)
                            )
                        }
                        Box(modifier = Modifier.padding(10.dp,10.dp,10.dp,0.dp)) {

                            val state by viewModel.chipUiState.collectAsState()


                            GameCategory(state.chips,
                                selectedIndex = state.selectedIndex,
                                onItemClick = { index -> viewModel.onChipSelected(index) })


                        }


                        TopGameUI(viewModel)

                        BestOfAllTimeUI(viewModel)



                    }*/


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


@Composable
fun TopGameUI(viewModel : HomeScreenViewModel,navigationController: NavHostController,sharedViewModel: SharedViewModel){
    val state by viewModel.topGame.collectAsState()

    Column {
        Box(modifier = Modifier.padding()) {

            Text(
                text = "Top Games",


                fontSize = 22.sp,
                modifier = Modifier.padding(20.dp, 10.dp, 0.dp, 10.dp)
            )
        }

        TopGameList(state.topGames,
            selectedIndex = 0,
            onItemClick = { index -> /*viewModel.onChipSelected(index)*/
                sharedViewModel.setGameData(state.topGames[index])
                navigationController.navigate(Routes.GameView.route)
    })

    }


}

@Composable
fun TopGameList(
    itemList: List<TopGameEntity.Result>,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit = {}
){
    LazyRow  {

        itemsIndexed(itemList) { index, item ->
            val isSelected = index == selectedIndex

            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .width(150.dp)
                    .height(200.dp)
                    .clickable {
                        onItemClick(index) },
                     elevation = CardDefaults.cardElevation(8.dp)



                ){
                Box(
                 modifier =  Modifier.background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xAA1A1A1A), // Semi-transparent dark brown
                                Color(0xFF0F0A05)  // Deep vintage brown
                            )
                        )
                        )
                ) {
                    // ImageView equivalent
                    AsyncImage(
                        model = item.background_image,
                        contentDescription = item.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop,
           /*             placeholder = painterResource(R.drawable.placeholder_game), // Add your placeholder
                        error = painterResource(R.drawable.error_image)*/
                    )
// 2. Dark gradient overlay for text readability
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .height(60.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.8f)
                                    )
                                )
                            )
                    ) {


                        Text(
                            text = item.name,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .align(Alignment.BottomStart),
                            fontWeight = FontWeight.Bold

                        )
                    }

                }

            }

        }
    }
}

@Composable
fun BestOfAllTimeUI(viewModel : HomeScreenViewModel){

    Column {
        Box(modifier = Modifier.padding()) {

            Text(
                text = "Best of All Time",


                fontSize = 22.sp,
                modifier = Modifier.padding(20.dp, 10.dp, 0.dp, 10.dp)
            )
        }

        BestOfAllTimeList(arrayListOf("Item 1", "Item 2", "Item 3"),
            selectedIndex = 0,
            onItemClick = { index -> /*viewModel.onChipSelected(index)*/ })

    }


}

@Composable
fun BestOfAllTimeList(
    itemList: List<String>,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit = {}
){
    LazyColumn()  {

        itemsIndexed(itemList) { index, item ->
            val isSelected = index == selectedIndex

            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(100.dp)
                    .clickable { onItemClick(index) },



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


