package com.example.mock1exam.views.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mock1exam.R
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.views.reusables.*

@Composable
fun BananaSubmitScreen(
    nagivateToBananaListing : () -> Unit
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
                        Text(text = "my banana", style = MaterialTheme.typography.h1)
                    }
                },
                contentPadding = PaddingValues(vertical = Dm.marginSmall),
                elevation = 0.dp
            )
        },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        BananaSubmitScreenContent(
            nagivateToBananaListing
        )
    }
}

@Composable
private fun BananaSubmitScreenContent(
    nagivateToBananaListing : () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dm.marginMedium)
    ) {
        AppBananaCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            SubmitForm(
                nagivateToBananaListing,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dm.marginMedium)
            )
        }
    }
}


@Composable
private fun SubmitForm(
    nagivateToBananaListing : () -> Unit,
    modifier: Modifier = Modifier
) {
    var bananaType by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var desciption by remember { mutableStateOf("") }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
            modifier = modifier
        ) {
            Spacer(modifier = Modifier.height(Dm.marginMedium))

            // banana type
            CustomTextField(
                placeHolderText = "banana type",
                value = bananaType
            ) { bananaType = it }

            Spacer(modifier = Modifier.height(Dm.marginMedium))

            // quantity
            CustomTextField(
                placeHolderText = "quantity",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = quantity.toString()
            ) {
                quantity = it.replace(".", "")
            }

            Spacer(modifier = Modifier.height(Dm.marginMedium))

            // price
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomTextField(
                    placeHolderText = "price",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = price,
                    modifier = Modifier.weight(4f)
                ) {
                    // TODO: Add float validation
                    price = it
                }

                Text(
                    text = "CAD",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End
                )
            }

            Spacer(modifier = Modifier.height(Dm.marginMedium))

            // location
            CustomTextField(
                placeHolderText = "location",
                value = location
            ) { location = it }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.near_me),
                    contentDescription = null,
                    modifier = Modifier.size(Dm.marginSmall)
                )
                Text(
                    text = "use my location",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = Dm.marginTiny)
                )

            }

            Spacer(modifier = Modifier.height(Dm.marginSmall))

            //description
            CustomTextField(
                placeHolderText = "description",
                value = desciption,
                maxLines = 5,
                modifier = Modifier.height(120.dp)
            ) { desciption = it }

            Spacer(modifier = Modifier.height(Dm.marginMedium))

            // Camara and Add Icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                JumboImageButton(imageResource = R.drawable.camera){
                    // pressed camera button
                }

                Spacer(modifier = Modifier.width(Dm.marginLarge))

                JumboIconButton(imageVector = Icons.Filled.Add){
                    // pressed add button
                }
            }

            Spacer(modifier = Modifier.height(Dm.marginMedium))

            // Submit button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                AppButton(
                    label = "submit",
                    padding = Dm.marginSmall,
                    modifier = Modifier.width(Dm.buttonWidthDefault)
                ) {
                    // on submit pressed
                    nagivateToBananaListing()
                }
            }

            Spacer(modifier = Modifier.height(Dm.marginLarge))
            Spacer(modifier = Modifier.height(Dm.marginLarge))
            Spacer(modifier = Modifier.height(Dm.marginLarge))
        }
}

@Composable
fun JumboImageButton(
    imageResource: Int = R.drawable.ic_launcher_foreground,
    modifier: Modifier = Modifier,
    onPress: () -> Unit
) {
    Box(
        modifier = modifier
            .width(140.dp)
            .height(110.dp)
            .border(
                width = Dm.borderWidth,
                color = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.medium
            )
            .clickable {
                onPress()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier
                .size(90.dp)
                .padding(Dm.marginSmall)
        )
    }
}

@Composable
fun JumboIconButton(
    imageVector: ImageVector = Icons.Default.Add,
    modifier: Modifier = Modifier,
    onPress: () -> Unit
) {
    Box(
        modifier = modifier
            .width(140.dp)
            .height(110.dp)
            .border(
                width = Dm.borderWidth,
                color = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.medium
            )
            .clickable {
                onPress()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier
                .size(90.dp)
                .padding(Dm.marginSmall)
        )
    }
}