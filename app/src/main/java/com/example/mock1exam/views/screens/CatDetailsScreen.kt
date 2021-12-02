package com.example.mock1exam.views.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mock1exam.R
import com.example.mock1exam.data.CatAPI.CatService
import com.example.mock1exam.data.CatAPI.responses.CatResponse
import com.example.mock1exam.data.FakeCatItem
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.utils.Resource
import com.example.mock1exam.views.reusables.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun CatDetailsScreen(
    navController: NavController,
    catId: String
) {
    Scaffold(
        backgroundColor = Color.White
    ) {
        CatDetailsScreenContent(catId)
    }
}

@ExperimentalFoundationApi
@Composable
private fun CatDetailsScreenContent(catId: String) {
    var scope = rememberCoroutineScope()
    var cat by remember { mutableStateOf<CatResponse?>(null) }

    LaunchedEffect(key1 = true) {
        val catService = CatService()

        scope.launch(Dispatchers.IO) {
            var resource = catService.getCatsByBreed(
                breed = "",
                limit = 5
            )

            if (resource is Resource.Success<*>) {
                if (resource.data!!.isNotEmpty()) {
                    cat = resource.data!!.filter {
                        it.id == catId
                    }[0]
                }
            } else {
                Log.d("debug", resource.message!!)
            }
        }
    }

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
                url = cat?.image?.url?:"",
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
                        text = cat?.name?:"",
                        style = MaterialTheme.typography.body1,
                        fontSize = 40.sp,
                        color = MaterialTheme.colors.primary
                    )

                    // row of icons
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
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
                        text = cat?.description?:"",
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