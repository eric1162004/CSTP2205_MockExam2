package com.example.mock1exam.views.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mock1exam.R
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.views.navigation.Screen
import com.example.mock1exam.views.reusables.*

@Composable
fun SignUpScreen(
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
                            text = "SIGN",
                            style = MaterialTheme.typography.body1,
                            fontSize = 40.sp,
                            color = MaterialTheme.colors.primary
                        )
                        Text(
                            text = "UP",
                            style = MaterialTheme.typography.h1,
                            color = MaterialTheme.colors.secondary
                        )
                    }
                },
                contentPadding = PaddingValues(vertical = Dm.marginExtraLarge),
                elevation = 0.dp,
                backgroundColor = Color.White
            )
        },
        backgroundColor = Color.White
    ) {
        SignUpScreenContent(navController)
    }
}

@Composable
private fun SignUpScreenContent(
    navController: NavController
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = Dm.marginLarge * 2)
    ) {
        // name
        CustomTextFieldWithImageIcon(
            imageResourceId = R.drawable.cat_pink_paw_icon,
            padding = Dm.marginTiny,
            placerHolderTextColor = MaterialTheme.colors.secondary,
            placeHolderText = "name",
            value = name
        ) { name = it }

        Spacer(modifier = Modifier.height(Dm.marginLarge))

        // email
        CustomTextFieldWithImageIcon(
            imageResourceId = R.drawable.cat_pink_paw_icon,
            padding = Dm.marginTiny,
            placerHolderTextColor = MaterialTheme.colors.secondary,
            placeHolderText = "email",
            value = email
        ) { email = it }

        Spacer(modifier = Modifier.height(Dm.marginLarge))

        // password
        CustomTextFieldWithImageIcon(
            imageResourceId = R.drawable.cat_pink_paw_icon,
            padding = Dm.marginTiny,
            placerHolderTextColor = MaterialTheme.colors.secondary,
            placeHolderText = "password",
            visualTransformation = PasswordVisualTransformation(),
            value = password
        ) { password = it }

        Spacer(modifier = Modifier.height(Dm.marginLarge))

        // password
        CustomTextFieldWithImageIcon(
            imageResourceId = R.drawable.cat_pink_paw_icon,
            padding = Dm.marginTiny,
            placerHolderTextColor = MaterialTheme.colors.secondary,
            placeHolderText = "confirm password",
            visualTransformation = PasswordVisualTransformation(),
            value = confirmPassword
        ) { confirmPassword = it }

        Spacer(modifier = Modifier.height(Dm.marginLarge))

        // sign up button
        AppButton(
            label = "sign up",
            fontColor = Color.White,
            buttonColor = MaterialTheme.colors.secondary,
            modifier = Modifier.width(Dm.buttonWidthDefault)
        ) {
            // sign up pressed
            navController.navigate(Screen.SearchFriendListScreen.route)
        }
    }
}
