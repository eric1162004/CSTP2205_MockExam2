package com.example.mock1exam.views.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mock1exam.views.navigation.Screen
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalFoundationApi
@ExperimentalPermissionsApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
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