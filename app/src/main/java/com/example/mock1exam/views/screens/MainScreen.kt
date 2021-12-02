package com.example.mock1exam.views.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mock1exam.utils.auth.Auth
import com.example.mock1exam.views.navigation.Screen
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalFoundationApi
@ExperimentalPermissionsApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val auth by remember { mutableStateOf(Auth()) }

    val initialScreen =
        if (auth.currentUser === null)
            Screen.WelcomeScreen.route
        else Screen.SearchFriendListScreen.route

    NavHost(
        navController = navController,
        startDestination = initialScreen
    ) {
        composable(route = Screen.WelcomeScreen.route) {
            WelcomeScreen(navController)
        }

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController)
        }

        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(navController)
        }

        composable(route = Screen.SearchFriendListScreen.route) {
            SearchFriendListScreen(navController)
        }

        composable(route = Screen.MoreFriendsScreen.route) {
            MoreFriendsScreen(navController)
        }

        composable(route = Screen.CatDetailsScreen.route) {
            val id = it.arguments?.getString("id")

            CatDetailsScreen(navController, id!!)
        }

        composable(route = Screen.CatUploadScreen.route) {
            CatUploadScreen(navController)
        }
    }
}