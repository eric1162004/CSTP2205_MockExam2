package com.example.mock1exam.views.app_reusables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mock1exam.data.CatAPI.responses.CatResponse
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.views.reusables.AppImageWithUrl
import com.example.mock1exam.views.reusables.VerticalSpacer

@Composable
fun CatItemCard(
    cat: CatResponse,
    imageWidth: Dp = 200.dp,
    imageRatio: Float = 1f,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        // cat image
        AppImageWithUrl(
            url = cat.image.url,
            aspectRatio = imageRatio,
            width = imageWidth,
            modifier = Modifier.clip(shape = MaterialTheme.shapes.large)
        )

        VerticalSpacer(Dm.marginSmall)

        // cat name
        Text(
            text = if (cat.alt_names.isNotEmpty()) cat.alt_names else "no name",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary,
            fontSize = 20.sp
        )

        // cat breed
        Text(
            text = if (cat.name.isNotEmpty()) cat.name else "no name",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary
        )

        // cat age
        Text(
            text = "${cat.adaptability} years old",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary
        )

        // cat gender
        Text(
            text = "girl",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary
        )
    }
}