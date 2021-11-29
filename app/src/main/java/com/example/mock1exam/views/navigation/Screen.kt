package com.example.mock1exam.views.navigation

sealed class Screen(val route: String){
    object LoginScreen : Screen("LoginScreen")
    object SignUpScreen : Screen("SignUpScreen")
    object BananaSubmitScreen : Screen("BananaSubmitScreen")
    object BananaMapScreen : Screen("BananaMapScreen")
    object BananaListingScreen : Screen("BananaListingScreen")
    object BananaDetailsScreen : Screen("BananaDetailsScreen")
}
