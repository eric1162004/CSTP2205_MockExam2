package com.example.mock1exam.views.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.mock1exam.R

@Composable
fun AppBananaCard(
    defaultStyle: Boolean = true,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    if(defaultStyle){
        Column(
            modifier = modifier
        ) {
            Image(
                painter = painterResource(id = R.drawable.banana_card1_top),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier.fillMaxWidth().background(Color.White)
            ) {
                content()
            }
            Image(
                painter = painterResource(id = R.drawable.banana_card1_bottom),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }else{
        Column(
            modifier = modifier
        ) {
            Image(
                painter = painterResource(id = R.drawable.banana_card2_top),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier.fillMaxWidth().background(Color.White)
            ) {
                content()
            }
            Image(
                painter = painterResource(id = R.drawable.banana_card2_bottom),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()

            )
        }
    }
}