package com.example.mock1exam.views.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mock1exam.R
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.views.app_reusables.JumboIconButton
import com.example.mock1exam.views.reusables.AppButton
import com.example.mock1exam.views.reusables.CustomTextFieldWithImageIcon
import com.example.mock1exam.views.reusables.VerticalSpacer

@Composable
fun CatUploadScreen(
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
                            text = "UPLOAD",
                            style = MaterialTheme.typography.body1,
                            fontSize = 40.sp,
                            color = MaterialTheme.colors.primary
                        )
                        Text(
                            text = "CAT",
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
        CatUploadScreenContent()
    }
}

@Composable
fun CatUploadScreenContent() {
    var breed by remember { mutableStateOf("") }
    var fact by remember { mutableStateOf("") }


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dm.marginLarge)

    ) {
        JumboIconButton(iconResourceId = R.drawable.camera) {
            // camera button pressed
        }

        VerticalSpacer(Dm.marginMedium)

        // breed
        CustomTextFieldWithImageIcon(
            padding = Dm.marginTiny,
            placeHolderText = "breed",
            placerHolderTextColor = MaterialTheme.colors.secondary,
            value = breed
        ) { breed = it }

        VerticalSpacer(Dm.marginMedium)

        CustomTextFieldWithImageIcon(
            padding = Dm.marginTiny,
            placeHolderText = "fact",
            placerHolderTextColor = MaterialTheme.colors.secondary,
            value = fact,
            maxLines = 4,
            modifier = Modifier.height(Dm.marginExtraLarge * 2)
        ) { fact = it }

        VerticalSpacer(Dm.marginLarge * 3)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            AppButton(
                label = "submit",
                fontColor = Color.White,
                buttonColor = MaterialTheme.colors.secondary,
                modifier = Modifier.width(Dm.buttonWidthDefault)
            ) {
                // submit pressed
            }
        }


    }

}

