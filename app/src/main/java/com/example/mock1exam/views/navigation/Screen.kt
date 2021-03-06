package com.example.mock1exam.views.navigation

sealed class Screen(val route: String){
    object WelcomeScreen : Screen("WelcomeScreen")

    object LoginScreen : Screen("LoginScreen")
    object SignUpScreen : Screen("SignUpScreen")

    object SearchFriendListScreen : Screen("SearchFriendListScreen")
    object MoreFriendsScreen : Screen("MoreFriendsScreen")

    object CatDetailsScreen : Screen("CatDetailsScreen/{id}"){
        fun createRoute(id: String) = "CatDetailsScreen/$id"
    }

    object CatUploadScreen : Screen("CatUploadScreen")
}
