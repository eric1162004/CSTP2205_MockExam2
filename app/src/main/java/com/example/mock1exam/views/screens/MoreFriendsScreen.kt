package com.example.mock1exam.views.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mock1exam.data.CatAPI.CatService
import com.example.mock1exam.data.CatAPI.responses.CatResponse
import com.example.mock1exam.data.FakeCatLists
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.utils.Resource
import com.example.mock1exam.views.app_reusables.CatItemCard
import com.example.mock1exam.views.navigation.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun MoreFriendsScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "MORE",
                            style = MaterialTheme.typography.body1,
                            fontSize = 40.sp,
                            color = MaterialTheme.colors.primary
                        )
                        Text(
                            text = "FRIENDS",
                            style = MaterialTheme.typography.h1,
                            color = MaterialTheme.colors.secondary
                        )
                    }
                },
                contentPadding = PaddingValues(vertical = Dm.marginLarge),
                elevation = 0.dp,
                backgroundColor = Color.White
            )
        },
        backgroundColor = Color.White
    ) {
        MoreFriendsScreenContent(navController)
    }
}

@ExperimentalFoundationApi
@Composable
private fun MoreFriendsScreenContent(
    navController: NavController
) {
    var cats by remember { mutableStateOf(listOf<CatResponse>()) }

    var scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        val catService = CatService()

        scope.launch(Dispatchers.IO) {
            var resource = catService.getCatsByBreed(
                breed = "",
                limit = 5
            )

            if (resource is Resource.Success<*>) {
                if (resource.data!!.isNotEmpty()) {
                    cats = resource.data!!
                }
            } else {
                Log.d("debug", resource.message!!)
            }
        }
    }

    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ){
        items(cats.size){ index ->
            CatItemCard(
                cat = cats[index],
                modifier = Modifier.padding(horizontal = Dm.marginSmall)
            ){
                // on card pressed
                navController.navigate(Screen.CatDetailsScreen.createRoute(cats[index].id))
            }
        }
    }
}
