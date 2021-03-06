package com.example.mock1exam.views.app_reusables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mock1exam.data.entities.Cat
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.views.reusables.AppImageWithUrl
import com.example.mock1exam.views.reusables.VerticalSpacer

@Composable
fun CatItemCard(
    cat: Cat,
    imageWidth: Dp = 200.dp,
    imageRatio: Float = 1f,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onClick() }
    ) {
        // cat image
        AppImageWithUrl(
            url = cat.imageUrl,
            aspectRatio = imageRatio,
            width = imageWidth,
            modifier = Modifier.clip(shape = MaterialTheme.shapes.large)
        )

        VerticalSpacer(Dm.marginSmall)

        // cat name
        Text(
            text = if (cat.name.isNotEmpty()) cat.name else "no name",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary,
            fontSize = 20.sp
        )

        // cat breed
        Text(
            text = if (cat.breed.isNotEmpty()) cat.breed else "unknown breed",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary
        )

        // cat age
        Text(
            text = "${cat.age} years old",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary
        )

        // cat gender
        Text(
            text = cat.gender,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary
        )
    }
}