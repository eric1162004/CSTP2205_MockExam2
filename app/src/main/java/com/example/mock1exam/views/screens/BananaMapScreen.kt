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
import com.example.assignment4.utils.map.Coordinate
import com.example.mock1exam.R
import com.example.mock1exam.data.FakeBananaItem
import com.example.mock1exam.data.FakeBananaItems
import com.example.mock1exam.data.entities.BananaItem
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.utils.map.AppMapView
import com.example.mock1exam.views.reusables.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Composable
fun BananaMapScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "map", style = MaterialTheme.typography.h1)
                    }
                },
                contentPadding = PaddingValues(vertical = Dm.marginSmall),
                elevation = 0.dp
            )
        },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        BananaMapScreenContent()
    }
}

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Composable
private fun BananaMapScreenContent() {
    var bananaItem by remember { mutableStateOf(FakeBananaItem) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dm.marginMedium)
    ) {
        AppBananaCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(horizontal = Dm.marginSmall)
                    .offset(y = -(Dm.marginLarge))
            ) {
                // map here
                AppMapView(
                    coordinates = listOf(bananaItem.location.coordinate),
                    flyToLocation = bananaItem.location.coordinate,
                    modifier = Modifier.aspectRatio(1f)
                )

                Spacer(modifier = Modifier.height(Dm.marginMedium))

                // map info

                // placeName
                Text(text = bananaItem.location.placeName, style = MaterialTheme.typography.h5)
                
                Spacer(modifier = Modifier.height(Dm.marginTiny))

                // address
                Text(text = bananaItem.location.address, style = MaterialTheme.typography.body1)

                Spacer(modifier = Modifier.height(Dm.marginSmall))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.fillMaxWidth()
                ){
                    // location image
                    AppImageWithUrl(
                        url = "https://www.digisystem" +
                                ".com/ca/en/case-studies/20190711060317/news_file_global/file/Masoutis%2001.jpg",
                        modifier = Modifier.weight(2f)
                    )

                    Spacer(modifier = Modifier.width(Dm.marginTiny).weight(.5f))

                    // Direction button
                    AppButton(
                        label = "direction",
                        modifier = Modifier.weight(1f)
                    ) {
                        // on direction pressed
                    }
                }
            }
        }
    }
}
