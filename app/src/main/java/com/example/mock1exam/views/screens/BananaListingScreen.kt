package com.example.mock1exam.views.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mock1exam.R
import com.example.mock1exam.data.FakeBananaItems
import com.example.mock1exam.data.entities.BananaItem
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.views.reusables.AppBananaCard
import com.example.mock1exam.views.reusables.AppImageWithUrl
import com.example.mock1exam.views.reusables.CircularImageButton
import com.example.mock1exam.views.reusables.CustomTextField

@ExperimentalFoundationApi
@Composable
fun BananaListingScreen(
    navigateToBananaDetailsScreen: () -> Unit
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
                        Text(text = "bananas", style = MaterialTheme.typography.h1)
                    }
                },
                contentPadding = PaddingValues(vertical = Dm.marginSmall),
                elevation = 0.dp
            )
        },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        BananaListingScreenContent(
            navigateToBananaDetailsScreen
        )
    }
}

@ExperimentalFoundationApi
@Composable
private fun BananaListingScreenContent(
    navigateToBananaDetailsScreen: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dm.marginMedium)
    ) {
        AppBananaCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            BananaListingSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dm.marginMedium),
                navigateToBananaDetailsScreen = navigateToBananaDetailsScreen
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun BananaListingSection(
    modifier: Modifier = Modifier,
    navigateToBananaDetailsScreen: () -> Unit,
) {
    var bananaItems by remember { mutableStateOf(FakeBananaItems) }
    var searchText by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End,
        modifier = modifier
    ) {
        // Search bar
        CustomTextField(
            imageVector = Icons.Filled.Search,
            placeHolderText = "banana type",
            value = searchText
        ) { searchText = it }

        Spacer(modifier = Modifier.height(Dm.marginMedium))

        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
            items(bananaItems.size) { index ->
                BananaListItem(bananaItems[index]) {
                    navigateToBananaDetailsScreen()
                }
            }
        }

    }
}

@Composable
fun BananaListItem(
    bananaItem: BananaItem,
    onPressed: (id: Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dm.marginTiny)
            .clickable {
                onPressed(bananaItem.id)
            },
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AppImageWithUrl(url = bananaItem.imageUrl)

            Spacer(modifier = Modifier.height(Dm.marginTiny))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                ) {
                    Text(text = bananaItem.name, style = MaterialTheme.typography.h5)
                    Text(text = "$${bananaItem.price}", style = MaterialTheme.typography.h5)
                }
                CircularImageButton(
                    imageResource = R.drawable.shopping_icon,
                    buttonSize = 40.dp
                ) {
                    // on shopping icon pressed
                }
            }
        }
    }
}


