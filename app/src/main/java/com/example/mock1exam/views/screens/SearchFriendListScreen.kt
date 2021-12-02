package com.example.mock1exam.views.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mock1exam.data.FakeCatBreedList
import com.example.mock1exam.data.entities.Cat
import com.example.mock1exam.data.repositories.CatRepository
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.data.Resource
import com.example.mock1exam.views.app_reusables.CatItemCard
import com.example.mock1exam.views.navigation.Screen
import com.example.mock1exam.views.reusables.*

@Composable
fun SearchFriendListScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Dm.marginMedium),
                    ) {
                        Text(
                            text = "SEARCH",
                            style = MaterialTheme.typography.body1,
                            fontSize = 40.sp,
                            color = MaterialTheme.colors.primary
                        )

                        HorizontalSpacer(Dm.marginSmall)

                        Text(
                            text = "FRIEND",
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
        floatingActionButton = {
            CircularIconButton(imageVector = Icons.Filled.Add) {
                navController.navigate(Screen.CatUploadScreen.route)
            }
        },
        backgroundColor = Color.White
    ) {
        SearchFriendListScreenContent(navController)
    }
}

@Composable
private fun SearchFriendListScreenContent(
    navController: NavController
) {
    val catRepository by remember { mutableStateOf(CatRepository()) }
    val catsFlowState = catRepository.getAll()
        .collectAsState(initial = listOf<Cat>()).value

    var cats by remember { mutableStateOf(listOf<Cat>()) }
    var filteredCats by remember { mutableStateOf(listOf<Cat>()) }

    var searchText by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(key1 = catsFlowState) {
        when (catsFlowState) {
            is Resource.Success<*> -> {
                catsFlowState.data?.let {
                    cats = catsFlowState.data as List<Cat>
                    filteredCats = cats
                }
            }
            else -> {
                errorMessage = "Something is wrong.."
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dm.marginMedium)
    ) {
        // search bar
        CustomTextField(
            imageVector = Icons.Filled.Search,
            imageVectorTint = MaterialTheme.colors.secondary,
            textFieldBorderRadius = MaterialTheme.shapes.medium,
            padding = Dm.marginTiny,
            placeHolderText = "search",
            placerHolderTextColor = MaterialTheme.colors.secondary,
            value = searchText
        ) {
            searchText = it

            if (searchText.isNotEmpty()) {
                filteredCats = cats.filter { cat ->
                    cat.name.lowercase()
                        .contains(searchText.lowercase())
                }
            } else {
                filteredCats = cats
            }
        }

        VerticalSpacer(Dm.marginMedium)

        // Filters row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppDropdownMenu(
                menuLabel = "breed",
                fontColor = MaterialTheme.colors.secondary,
                dropDownItems = FakeCatBreedList
            ) { selectedIndex ->
                // on breed selected
                filteredCats = cats.filter { catResponse ->
                    catResponse.breed.lowercase()
                        .contains(FakeCatBreedList[selectedIndex].lowercase())
                }
            }

            HorizontalSpacer(Dm.marginSmall)

            AppDropdownMenu(
                menuLabel = "category",
                fontColor = MaterialTheme.colors.secondary,
                dropDownItems = FakeCatBreedList
            ) {
                // on breed selected
            }
        }

        VerticalSpacer(Dm.marginExtraLarge)

        // lazy row
        LazyRow {
            items(filteredCats.size) { index ->
                CatItemCard(
                    cat = filteredCats[index],
                    modifier = Modifier.padding(horizontal = Dm.marginSmall)
                ) {
                    // on card pressed
                    navController.navigate(Screen.CatDetailsScreen.createRoute(filteredCats[index].id))
                }
            }
        }

        VerticalSpacer(Dm.marginExtraLarge)

        // more button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppButton(
                label = "more",
                buttonWidth = 80.dp,
                fontColor = Color.White,
                buttonColor = MaterialTheme.colors.secondary,
            ) {
                // more pressed
                navController.navigate(Screen.MoreFriendsScreen.route)
            }
        }
    }
}

