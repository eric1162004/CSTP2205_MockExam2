package com.example.mock1exam

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.mock1exam.ui.theme.Mock1ExamTheme
import com.example.mock1exam.views.screens.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi

class MainActivity : ComponentActivity() {
    @ExperimentalPermissionsApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Temp Fix to hide System Status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        setContent {
            Mock1ExamTheme {
//                MainScreen()

//                LoginScreen()
//                SignUpScreen()
//                BananaSubmitScreen()
//                BananaListingScreen()
//                BananaDetailsScreen()
//                BananaMapScreen()

                WelcomeScreen()
            }
        }
    }
}



