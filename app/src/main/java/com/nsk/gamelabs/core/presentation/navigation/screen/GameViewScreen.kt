package com.nsk.gamelabs.core.presentation.navigation.screen

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.nsk.gamelabs.core.data.remote.model.GameDescriptionEntity
import com.nsk.gamelabs.core.data.remote.model.TopGameEntity
import com.nsk.gamelabs.core.presentation.viewModel.GameDetailViewModel
import com.nsk.gamelabs.core.presentation.viewModel.SharedViewModel
import com.nsk.gamelabs.ui.theme.DarkerGrey
import kotlinx.coroutines.flow.observeOn

@Composable
fun GameViewScreen(   viewModel: GameDetailViewModel = hiltViewModel(),
                      sharedViewModel: SharedViewModel =hiltViewModel(),
                      navController: NavHostController
) {
    val gameResult = sharedViewModel.gameData.collectAsStateWithLifecycle()

    viewModel.getDescription(gameResult.value?.id!!.toInt())
    val getDetails by viewModel.details.collectAsStateWithLifecycle()

    var openWebsite by remember { mutableStateOf("") }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        item {

            Column() {
                AsyncImage(
                    model = gameResult.value?.background_image,
                    contentDescription = gameResult.value?.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(3.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop,
                    /*             placeholder = painterResource(R.drawable.placeholder_game), // Add your placeholder
                                 error = painterResource(R.drawable.error_image)*/
                )


                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding( top = 20.dp, bottom = 20.dp),
                    text = gameResult.value?.name.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )


                Button (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding( 10.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    onClick = {
                        openWebsite=getDetails?.website.toString()

                    }
                ){
                    Text(
                        text = "Download",
                        fontSize = 16.sp
                    )
                    if(!openWebsite.isEmpty()){
                       WebViewScreen(openWebsite)
                        openWebsite=""
                    }
                }


                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp),
                    text = "Gameplay",
                )

                LoadGamePlayImages(gameResult.value?.short_screenshots!!,
                    selectedIndex = 0,
                    onItemClick = {})

               GameDescription(
                   viewModel = viewModel, getDetails = getDetails)

            }


        }


    }

}

@Composable
fun LoadGamePlayImages(
    list: List<TopGameEntity.Result.ShortScreenshot>,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit = {}

    ) {

        val itemList =list.drop(1)
        LazyRow  {

            itemsIndexed(itemList) { index, item ->
                val isSelected = index == selectedIndex

                AsyncImage(
                    model = item.image,
                    contentDescription = item.id.toString(),
                    modifier = Modifier
                        .width(250.dp)
                        .height(150.dp)
                        .padding(3.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop,
                    /*             placeholder = painterResource(R.drawable.placeholder_game), // Add your placeholder
                                 error = painterResource(R.drawable.error_image)*/
                )

            }
        }
    }

@Composable
fun GameDescription(viewModel: GameDetailViewModel,getDetails : GameDescriptionEntity?){


    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = 20.dp,
                bottom = 20.dp
            ),
        text = getDetails?.description_raw ?: "",
    )

    Text(
        modifier = Modifier
            .padding(10.dp),
        text = "Geners",
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    )

  //add grid data
    // ✅ 3-COLUMN GRID (Safe inside LazyColumn!)
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),  // Exactly 3 columns!
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),  // Fixed height = NO CRASH!
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        // Sample grid data
        items(getDetails?.genres?.size ?: 0) { number ->
            Card(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                   // .aspectRatio(1f),  // Square cards!
                colors = CardDefaults.cardColors(containerColor = DarkerGrey)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { /* onClick */ },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = getDetails?.genres[number]?.name ?: "",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )

                }
            }
        }
    }

}

@Composable
fun WebViewScreen(url: String) {
    AndroidView(
        modifier = Modifier.fillMaxSize(), // Use Modifier to apply standard compose modifiers
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient() // Ensures links open in the app's WebView
                settings.javaScriptEnabled = true // Enable JavaScript if needed
            }
        },
        update = { webView ->
            webView.loadUrl(url)
        }
    )
}



