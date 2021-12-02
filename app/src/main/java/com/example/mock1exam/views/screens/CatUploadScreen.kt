package com.example.mock1exam.views.screens

import android.Manifest
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toFile
import androidx.navigation.NavController
import com.example.foodvillage2205.view.composables.CameraCapture
import com.example.mock1exam.R
import com.example.mock1exam.data.CatAPI.CatService
import com.example.mock1exam.data.Resource
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.utils.gallery.GallerySelect
import com.example.mock1exam.utils.permission.Permission
import com.example.mock1exam.views.app_reusables.JumboIconButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.firebase.firestore.util.FileUtil
import com.google.rpc.context.AttributeContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.source
import java.io.File
import android.provider.MediaStore
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.net.toUri
import com.example.foodvillage2205.model.repositories.StorageRepository
import com.example.mock1exam.data.entities.Cat
import com.example.mock1exam.data.repositories.CatRepository
import com.example.mock1exam.views.reusables.*

@ExperimentalPermissionsApi
@Composable
fun CatUploadScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "UPLOAD ",
                            style = MaterialTheme.typography.body1,
                            fontSize = 40.sp,
                            color = MaterialTheme.colors.primary
                        )
                        Text(
                            text = "CAT",
                            style = MaterialTheme.typography.h1,
                            color = MaterialTheme.colors.secondary
                        )
                    }
                },
                contentPadding = PaddingValues(
                    vertical = Dm.marginLarge,
                    horizontal = Dm.marginMedium
                ),
                elevation = 0.dp,
                backgroundColor = Color.White
            )
        },
        backgroundColor = Color.White
    ) {
        CatUploadScreenContent(navController)
    }
}

@ExperimentalPermissionsApi
@Composable
fun CatUploadScreenContent(
    navController: NavController
) {
    var showCamera by remember { mutableStateOf(false) }
    var showGallery by remember { mutableStateOf(false) }

    var scope = rememberCoroutineScope()
    var catRepository by remember { mutableStateOf(CatRepository()) }
    var storageRepository by remember { mutableStateOf(StorageRepository()) }

    var breed by remember { mutableStateOf("") }
    var fact by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var age by remember { mutableStateOf<Int?>(null) }

    var imageFile by remember { mutableStateOf<File?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    if (!showCamera && !showGallery) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dm.marginLarge)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
//                if (imageFile != null) {
//                    AppImageWithUrl(url = imageFile!!.toUri().toString())
//                } else {
//                    JumboIconButton(iconResourceId = R.drawable.camera) {
//                        // camera button pressed
//                        showCamera = true
//                    }
//                }

                if (imageUri != null) {
                    AppImageWithUrl(
                        url = imageUri!!.toString(),
                        modifier = Modifier.clickable {
                            showGallery = true
                        }
                    )
                } else {
                    JumboIconButton(iconResourceId = R.drawable.add) {
                        // gallery button pressed
                        showGallery = true
                    }
                }
            }

            VerticalSpacer(Dm.marginMedium)

            // breed
            CustomTextFieldWithImageIcon(
                padding = Dm.marginTiny,
                placeHolderText = "breed",
                placerHolderTextColor = MaterialTheme.colors.secondary,
                value = breed
            ) { breed = it }

            VerticalSpacer(Dm.marginMedium)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                CustomTextFieldWithImageIcon(
                    padding = Dm.marginTiny,
                    placeHolderText = "sex",
                    placerHolderTextColor = MaterialTheme.colors.secondary,
                    value = gender,
                    modifier = Modifier.weight(1f)
                ) { gender = it }

                HorizontalSpacer(Dm.marginMedium)

                CustomTextFieldWithImageIcon(
                    padding = Dm.marginTiny,
                    placeHolderText = "age",
                    placerHolderTextColor = MaterialTheme.colors.secondary,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = age?.toString() ?: "",
                    modifier = Modifier.weight(1f)
                ) { age = it.toIntOrNull() }
            }

            VerticalSpacer(Dm.marginMedium)

            CustomTextFieldWithImageIcon(
                padding = Dm.marginTiny,
                placeHolderText = "fact",
                placerHolderTextColor = MaterialTheme.colors.secondary,
                value = fact,
                maxLines = 4,
                modifier = Modifier.height(Dm.marginExtraLarge * 2)
            ) { fact = it }

            VerticalSpacer(Dm.marginLarge * 3)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                AppButton(
                    label = "submit",
                    fontColor = Color.White,
                    buttonColor = MaterialTheme.colors.secondary,
                    modifier = Modifier.width(Dm.buttonWidthDefault)
                ) {
                    // submit pressed
                    scope.launch(Dispatchers.IO) {
                        // call network
                        storageRepository.uploadImage(imageUri!!) { url, fileName ->

                            Log.d("debug", url.toString())
                            Log.d("debug", fileName)

                            catRepository.createCat(
                                Cat(
                                    breed = breed,
                                    description = fact,
                                    gender = gender,
                                    age = age ?: 0,
                                    imageUrl = url.toString(),
                                    imageFileName = fileName
                                )
                            ) {
                                if (it is Resource.Success) {
                                    Log.d("debug", "upload successfully")
                                }
                            }
                        }

                    }
                }
            }
        }
    } else if (showCamera && !showGallery) {
        CameraCapture() {
            showCamera = false
            imageUri = null
            imageFile = it
        }
    } else {
        GallerySelect() {
            showGallery = false
            imageFile = null
            imageUri = it
        }
    }
}