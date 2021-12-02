package com.example.mock1exam.views.screens

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
import com.example.mock1exam.data.entities.Cat
import com.example.mock1exam.data.repositories.CatRepository
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.views.app_reusables.CatItemCard
import com.example.mock1exam.views.navigation.Screen

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
    val catRepository by remember { mutableStateOf(CatRepository()) }
    val catsFlowState = catRepository.getAll()
        .collectAsState(initial = listOf<Cat>()).value

    var cats by remember { mutableStateOf(listOf<Cat>()) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(key1 = catsFlowState) {
        when (catsFlowState) {
            is com.example.mock1exam.data.Resource.Success<*> -> {
                catsFlowState.data?.let {
                    cats = catsFlowState.data as List<Cat>
                }
            }
            else -> {
                errorMessage = "Something is wrong.."
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
