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

    NavHost(navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                onCreateAccountButtonPressed = {
                    navController.navigate(Screen.SignUpScreen.route)
                },
                onLoginButtonPressed = {
                    // On login button pressed
                    // TODO: set up authentication logic
                    navController.navigate(Screen.BananaSubmitScreen.route)
                }
            )
        }

        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(
                onBackButtonPressed = {
                    navController.navigate(Screen.LoginScreen.route)
                },
                onSignUpButtonPressed = {
                    // TODO: set up sign up logic
                    navController.navigate(Screen.BananaSubmitScreen.route)
                }
            )
        }

        composable(route = Screen.BananaSubmitScreen.route) {
            BananaSubmitScreen(
                nagivateToBananaListing = {
                    navController.navigate(Screen.BananaListingScreen.route)
                }
            )
        }

        composable(route = Screen.BananaListingScreen.route) {
            BananaListingScreen {
                navController.navigate(Screen.BananaDetailsScreen.route)
            }
        }

        composable(route = Screen.BananaDetailsScreen.route) {
            BananaDetailsScreen(
                nagivateToBananaMapScreen = {
                    navController.navigate(Screen.BananaMapScreen.route)
                }
            )
        }

        composable(route = Screen.BananaMapScreen.route) {
            BananaMapScreen()
        }
    }
}