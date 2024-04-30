package com.example.a3200finalproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun ArtObjectView(artObject: ArtObject?) {
    if (artObject != null) {
        Image(
            painter = rememberImagePainter(artObject.primaryImage),
            contentDescription = artObject.title,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
    } else {
        BasicText("Loading...")
    }
}