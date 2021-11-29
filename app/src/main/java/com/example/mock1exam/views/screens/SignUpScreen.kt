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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mock1exam.R
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.views.reusables.*

@Composable
fun SignUpScreen(
    onBackButtonPressed: () -> Unit,
    onSignUpButtonPressed: () -> Unit
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
                        Text(text = "login", style = MaterialTheme.typography.h1)
                    }
                },
                contentPadding = PaddingValues(vertical = Dm.marginSmall),
                elevation = 0.dp
            )
        },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        SignUpScreenContent(
            onBackButtonPressed,
            onSignUpButtonPressed
        )
    }
}

@Composable
private fun SignUpScreenContent(
    onBackButtonPressed: () -> Unit,
    onSignUpButtonPressed: () -> Unit
) {
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dm.marginMedium)
        ) {
            AppBananaCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                SignUpForm(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dm.marginMedium)
                )
            }

            AppBananaCard(
                defaultStyle = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 430.dp)
            ) {
                // bottom section
                Spacer(modifier = Modifier.height(Dm.marginExtraLarge))
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dm.marginMedium)
                .padding(Dm.marginMedium)
                .padding(Dm.marginSmall)
                .offset(y = (-200).dp)
        ) {
            CircularIconButton(
                imageVector = Icons.Filled.ArrowBack,
                modifier = Modifier.offset(y = 70.dp)
            ) {
                onBackButtonPressed()
            }

            CircularIconButton(
                imageVector = Icons.Filled.ArrowForward,
                modifier = Modifier
            ) {
                onSignUpButtonPressed()
            }
        }
    }
}

@Composable
private fun SignUpForm(
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
            modifier = modifier
        ) {
//            Spacer(modifier = Modifier.height(Dm.marginLarge))

            // name
            CustomTextField(
                imageVector = Icons.Filled.Person,
                padding = Dm.marginTiny,
                placeHolderText = "name",
                value = name
            ) { name = it }

            Spacer(modifier = Modifier.height(Dm.marginLarge))

            // email
            CustomTextField(
                imageVector = Icons.Filled.Email,
                padding = Dm.marginTiny,
                placeHolderText = "email",
                value = email
            ) { email = it }

            Spacer(modifier = Modifier.height(Dm.marginLarge))

            // password
            CustomTextField(
                imageVector = Icons.Filled.Lock,
                padding = Dm.marginTiny,
                placeHolderText = "password",
                visualTransformation = PasswordVisualTransformation(),
                value = password
            ) { password = it }

            Spacer(modifier = Modifier.height(Dm.marginLarge))

            // password
            CustomTextField(
                imageVector = Icons.Filled.Lock,
                padding = Dm.marginTiny,
                placeHolderText = "confirm password",
                visualTransformation = PasswordVisualTransformation(),
                value = confirmPassword
            ) { confirmPassword = it }

            Spacer(modifier = Modifier.height(Dm.marginLarge))
        }
    }
}