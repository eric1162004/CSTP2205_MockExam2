package com.example.mock1exam.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mock1exam.R

val itim = FontFamily(
    Font(R.font.itim_regular, FontWeight.Normal)
)

val kranky = FontFamily(
    Font(R.font.kranky_regular, FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = itim,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    body2 = TextStyle(
        fontFamily = itim,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    h1 = TextStyle(
        fontFamily = kranky,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    ),
    h2 = TextStyle(
        fontFamily = kranky,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    h3 = TextStyle(
        fontFamily = kranky,
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp
    ),
    h4 = TextStyle(
        fontFamily = kranky,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    h5 = TextStyle(
        fontFamily = kranky,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontFamily = kranky,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    button = TextStyle(
        fontFamily = itim,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    caption = TextStyle(
        fontFamily = itim,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
)