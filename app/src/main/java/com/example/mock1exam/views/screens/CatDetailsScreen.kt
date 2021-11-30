package com.example.mock1exam.views.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mock1exam.R
import com.example.mock1exam.data.FakeCatItem
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.views.reusables.*

@ExperimentalFoundationApi
@Composable
fun CatDetailsScreen(
    navController: NavController
) {
    Scaffold(
        backgroundColor = Color.White
    ) {
        CatDetailsScreenContent()
    }
}

@ExperimentalFoundationApi
@Composable
private fun CatDetailsScreenContent() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // image layer (base layer)
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppImageWithUrl(
                url = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws" +
                        ".com/images/cute-cat-photos-1593441022.jpg?crop=0.669xw:1.00xh;0.166xw,0&resize=640:*",
                modifier = Modifier.fillMaxWidth()
            )
        }

        // cat info layer (surface layer)
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            VerticalSpacer(modifier = Modifier.weight(1f))

            Card(
                elevation = 0.dp,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.weight(2f)
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dm.marginMedium)
                ) {
                    Text(
                        text = "Persian Cat",
                        style = MaterialTheme.typography.body1,
                        fontSize = 40.sp,
                        color = MaterialTheme.colors.primary
                    )

                    // row of icons
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                    ){
                        CircularImageButton(
                            imageResource = R.drawable.cat_face_icon,
                            backgroundColor = Color.White
                        ) {
                            // cat face button
                        }

                        CircularImageButton(
                            imageResource = R.drawable.cat_sleep_icon,
                            backgroundColor = Color.White
                        ) {
                            // cat sleep button
                        }

                        CircularImageButton(
                            imageResource = R.drawable.cat_heart_icon,
                            backgroundColor = Color.White
                        ) {
                            // cat heart button
                        }
                    }

                    VerticalSpacer(Dm.marginMedium)

                    // cat description
                    Text(
                        text = FakeCatItem.fact,
                        style = MaterialTheme.typography.body1
                    )

                    VerticalSpacer(Dm.marginExtraLarge)

                    // adopt button
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        AppButton(
                            label = "adopt",
                            buttonColor = MaterialTheme.colors.secondary,
                            fontColor = Color.White,
                            modifier = Modifier.width(Dm.buttonWidthDefault)
                        ) {
                            // on adopt pressed
                        }
                    }
                }
            }
        }
    }
}