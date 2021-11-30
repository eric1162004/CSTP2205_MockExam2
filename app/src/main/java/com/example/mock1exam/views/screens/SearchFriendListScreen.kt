package com.example.mock1exam.views.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mock1exam.data.CatAPI.CatService
import com.example.mock1exam.data.CatAPI.responses.CatResponse
import com.example.mock1exam.data.FakeCatBreedList
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.utils.Resource
import com.example.mock1exam.views.app_reusables.CatItemCard
import com.example.mock1exam.views.navigation.Screen
import com.example.mock1exam.views.reusables.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                contentPadding = PaddingValues(vertical = Dm.marginMedium),
                elevation = 0.dp,
                backgroundColor = Color.White
            )
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
    var searchText by remember { mutableStateOf("") }
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
        ) { searchText = it }

        VerticalSpacer(Dm.marginMedium)

        // Filters row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppDropdownMenu(menuLabel = "breed", dropDownItems = FakeCatBreedList) {
                // on breed selected
            }

            HorizontalSpacer(Dm.marginSmall)

            AppDropdownMenu(menuLabel = "category", dropDownItems = FakeCatBreedList) {
                // on breed selected
            }
        }

        VerticalSpacer(Dm.marginExtraLarge)

        // lazy row
        LazyRow {
            items(cats.size) { index ->
                CatItemCard(
                    cat = cats[index],
                    modifier = Modifier.padding(horizontal = Dm.marginSmall)
                )
            }
        }

        VerticalSpacer(Dm.marginMedium)

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

