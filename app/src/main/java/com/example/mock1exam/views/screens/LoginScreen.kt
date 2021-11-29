package com.example.mock1exam.views.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mock1exam.R
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.views.reusables.*

@Composable
fun LoginScreen(
    onCreateAccountButtonPressed: () -> Unit,
    onLoginButtonPressed: () -> Unit
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
        LoginScreenContent(
            onCreateAccountButtonPressed,
            onLoginButtonPressed
        )
    }
}

@Composable
private fun LoginScreenContent(
    onCreateAccountButtonPressed: () -> Unit,
    onLoginButtonPressed: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.BottomEnd
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dm.marginMedium)
        ) {
            AppBananaCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                LoginForm(
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
                CreateAccountSection(
                    onCreateAccountButtonPressed
                )
            }
        }

        CircularIconButton(
            imageVector = Icons.Filled.ArrowForward,
            modifier = Modifier
                .padding(Dm.marginMedium)
                .padding(Dm.marginMedium)
                .padding(Dm.marginSmall)
                .offset(y = (-(200.dp)))
        ) {
            // login button pressed
            onLoginButtonPressed()
        }
    }
}

@Composable
private fun LoginForm(
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isRememberMeChecked by remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
            modifier = modifier
        ) {
            // Depends on the Screen Size
            // Need this when in Full Screen Mode
//            Spacer(modifier = Modifier.height(Dm.marginLarge))

            // name
            CustomTextField(
                imageVector = Icons.Filled.Person,
                padding = Dm.marginTiny,
                placeHolderText = "name",
                value = name
            ) { name = it }

            Spacer(modifier = Modifier.height(Dm.marginLarge))

            // password
            CustomTextField(
                imageVector = Icons.Filled.Lock,
                padding = Dm.marginTiny,
                placeHolderText = "password",
                visualTransformation = PasswordVisualTransformation(),
                value = password
            ) { password = it }

            Spacer(modifier = Modifier.height(Dm.marginMedium))

            // remember me
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                CheckboxField(
                    label = "remember me",
                    checked = isRememberMeChecked
                ) {
                    isRememberMeChecked = !isRememberMeChecked
                }

                ClickableLink(
                    label = "Forget password",
                    style = MaterialTheme.typography.caption
                ) {
                    // TODO: On "Forget password?" Clicked
                }
            }

            Spacer(modifier = Modifier.height(Dm.marginLarge))
            Spacer(modifier = Modifier.height(Dm.marginMedium))

            // or sign in with
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "or sign in with",
                    modifier = Modifier
                        .weight(1.25f)
                        .padding(horizontal = Dm.marginSmall)
                )

                HorizontalDivider(
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(Dm.marginMedium))

            // facebook, google icons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                CircularImageButton(
                    backgroundColor = Color.White,
                    imageResource = R.drawable.facebook_icon
                ) {
                    //TODO: Facebook Login
                }

                Spacer(modifier = Modifier.width(Dm.marginSmall))

                CircularImageButton(
                    backgroundColor = Color.White,
                    imageResource = R.drawable.google_icon
                ) {
                    //TODO: Google Login

                }
            }
        }
    }
}

@Composable
private fun CreateAccountSection(
    onCreateAccountButtonPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dm.marginMedium)
    ) {
        Text(text = "Don't have an account?")

        Spacer(modifier = Modifier.height(Dm.marginMedium))

        AppButton(
            label = "create account",
            modifier = Modifier.fillMaxWidth()
        ) {
            // on create account button clicked
            onCreateAccountButtonPressed()
        }
    }
}



