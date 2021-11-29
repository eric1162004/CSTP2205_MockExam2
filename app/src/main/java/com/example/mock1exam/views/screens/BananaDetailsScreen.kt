package com.example.mock1exam.views.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.mock1exam.R
import com.example.mock1exam.data.FakeBananaItem
import com.example.mock1exam.data.entities.BananaItem
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.utils.map.AppMapView
import com.example.mock1exam.views.reusables.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalPermissionsApi
@Composable
fun BananaDetailsScreen(
    nagivateToBananaMapScreen: () -> Unit
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
        BananaDetailsScreenContent(
            nagivateToBananaMapScreen
        )
    }
}

@ExperimentalPermissionsApi
@Composable
private fun BananaDetailsScreenContent(
    nagivateToBananaMapScreen: () -> Unit
) {
    var bananaItem by remember { mutableStateOf(FakeBananaItem) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dm.marginMedium)
    ) {
        item {
            AppBananaCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                BananaDetailsSection(
                    bananaItem = bananaItem,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            AppBananaCard(
                defaultStyle = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = -80.dp)
            ) {
                // location info section
                BananaLocationSection(
                    bananaItem,
                    nagivateToBananaMapScreen
                )
            }
        }
    }
}

@ExperimentalPermissionsApi
@Composable
fun BananaLocationSection(
    bananaItem: BananaItem,
    nagivateToBananaMapScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(horizontal = Dm.marginSmall)
    ) {
        AppMapView(
            coordinates = listOf(bananaItem.location.coordinate),
            flyToLocation = bananaItem.location.coordinate,
            modifier = Modifier
                .aspectRatio(1.5f)
                .weight(1.25f)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(horizontal = Dm.marginSmall)
                .weight(1f)
        ) {
            // placeName
            Text(text = bananaItem.location.placeName, style = MaterialTheme.typography.h5)

            Spacer(modifier = Modifier.height(Dm.marginTiny))

            // address
            Text(text = bananaItem.location.address, style = MaterialTheme.typography.caption)

            Spacer(modifier = Modifier.height(Dm.marginSmall))

            // Direction button
            AppButton(
                label = "map",
                modifier = Modifier.align(Alignment.End)
            ) {
                // on direction pressed
                nagivateToBananaMapScreen()
            }
        }
    }
}

@Composable
private fun BananaDetailsSection(
    bananaItem: BananaItem,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.chevron_left),
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier
                .height(Dm.marginExtraLarge)
                .weight(.1f)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.weight(1f)
        ) {
            Text(text = bananaItem.name, style = MaterialTheme.typography.h1)

            Spacer(modifier = Modifier.height(Dm.marginMedium))

            AppImageWithUrl(url = bananaItem.imageUrl)

            Spacer(modifier = Modifier.height(Dm.marginSmall))

            Text(text = bananaItem.description, style = MaterialTheme.typography.body1)

            Spacer(modifier = Modifier.height(Dm.marginSmall))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "$ " + bananaItem.price, style = MaterialTheme.typography.h4)

                Spacer(modifier = Modifier.width(Dm.marginSmall))

                AppButton(label = "buy") {
                    // on buy button pressed
                }
            }

        }
        Icon(
            painter = painterResource(id = R.drawable.chevron_right),
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier
                .height(Dm.marginExtraLarge)
                .weight(.1f)
        )
    }
}