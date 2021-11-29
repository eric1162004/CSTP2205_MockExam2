package com.example.mock1exam.views.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mock1exam.R
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.views.reusables.AppImageWithResource

@Composable
fun WelcomeScreen(){
    Column(
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally,
       modifier = Modifier
           .fillMaxSize()
           .padding(Dm.marginMedium)
    ){
        // image
        AppImageWithResource(resourceId = R.drawable.welcome_page_cat)

        // title text

        // sub-text

        // meow icon
    }
}
